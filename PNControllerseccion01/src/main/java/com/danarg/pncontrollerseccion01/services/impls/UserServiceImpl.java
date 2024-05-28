package com.danarg.pncontrollerseccion01.services.impls;

import com.danarg.pncontrollerseccion01.domain.dtos.ChangePasswordDTO;
import com.danarg.pncontrollerseccion01.domain.dtos.UserEditDTO;
import com.danarg.pncontrollerseccion01.domain.dtos.UserRegisterDTO;
import com.danarg.pncontrollerseccion01.domain.dtos.UserResponseDTO;
import com.danarg.pncontrollerseccion01.domain.entities.Token;
import com.danarg.pncontrollerseccion01.domain.entities.User;
import com.danarg.pncontrollerseccion01.repositories.TokenRepository;
import com.danarg.pncontrollerseccion01.repositories.UserRepository;
import com.danarg.pncontrollerseccion01.services.UserService;
import com.danarg.pncontrollerseccion01.utils.JWTTools;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private TokenRepository tokenRepository;


    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByIdentifier(String identifier) {
        return userRepository.findByUsernameOrEmail(identifier, identifier).orElse(null);
    }

    @Override
    public User findByUsernameOrEmail(String username, String email) {
        return userRepository.findByUsernameOrEmail(username, email).filter(User::isEnabled).orElse(null);
    }

    @Override
    public User findByUUID(UUID uuid) {
        return userRepository.findById(uuid).filter(User::isEnabled).orElse(null);
    }

    @Override
    public List<UserResponseDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .filter(User::isEnabled)
                .map(user -> new UserResponseDTO(user.getUsername(), user.getEmail()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void register(UserRegisterDTO UserInfo) {
        User user = new User();

        user.setUsername(UserInfo.getUsername());
        user.setEmail(UserInfo.getEmail());
        user.setPassword(UserInfo.getPassword());
        user.setActive(true);

        userRepository.save(user);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void changePassword(ChangePasswordDTO info) {
        User user = findByIdentifier(info.getIdentifier());

        if (user != null) {
            user.setPassword(info.getNewPassword());
            userRepository.save(user);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void deleteUser(UUID uuid) {
        User user = findByUUID(uuid);

        if (user != null) {
            user.setActive(false);
            userRepository.save(user);
        }
    }

    @Override
    public boolean checkPassword(User user, String password) {
        return !user.getPassword().equals(password);
    }

    @Override
    public void edit(UserEditDTO info) {
        User user = findByIdentifier(info.getUsername());

        if (user != null) {
            user.setEmail(info.getEmail());
            userRepository.save(user);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Token registerToken(User user) throws Exception {
        cleanTokens(user);

        String tokenString = jwtTools.generateToken(user);
        Token token = new Token(tokenString, user);

        tokenRepository.save(token);

        return token;
    }

    @Override
    public Boolean isTokenValid(User user, String token) {
        try {
            cleanTokens(user);
            List<Token> tokens = tokenRepository.findByUserAndActive(user, true);

            tokens.stream()
                    .filter(tk -> tk.getContent().equals(token))
                    .findAny()
                    .orElseThrow(() -> new Exception());

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void cleanTokens(User user) throws Exception {
        List<Token> tokens = tokenRepository.findByUserAndActive(user, true);

        tokens.forEach(token -> {
            if(!jwtTools.verifyToken(token.getContent())) {
                token.setActive(false);
                tokenRepository.save(token);
            }
        });

    }

    @Override
    public User findOneByIdentifier(String username) {
        return userRepository.findByUsernameOrEmail(username, username).orElse(null);
    }

    @Override
    public User findUserAuthenticated() {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return userRepository.findOneByUsernameOrEmail(username, username);
    }
}
