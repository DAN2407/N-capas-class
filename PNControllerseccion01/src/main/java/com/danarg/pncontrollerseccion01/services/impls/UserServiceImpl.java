package com.danarg.pncontrollerseccion01.services.impls;

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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final JWTTools jwtTools;
    private final TokenRepository tokenRepository;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    public UserServiceImpl(UserRepository userRepository, JWTTools jwtTools, TokenRepository tokenRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtTools = jwtTools;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    @Transactional(rollbackOn = Exception.class)
    public void create(UserRegisterDTO info) {
        User user = new User();
        user.setUsername(info.getUsername());
        user.setEmail(info.getEmail());
        user.setPassword(passwordEncoder.encode(info.getPassword()));
        userRepository.save(user);
    }


    @Override
    public User findByIdentifier(String identifier) {
        return userRepository.findByUsernameOrEmail(identifier, identifier).orElse(null);
    }

    @Override
    public User findByUsernameOrEmail(String username, String email) {
        return userRepository.findByUsernameOrEmail(username, email).orElse(null);
    }


    @Override
    public void toggleEnable(String username) {
        User user = userRepository.findByUsernameOrEmail(username, username).orElse(null);
        assert user != null;
        user.setActive(!user.getActive());
        userRepository.save(user);
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
    public boolean isActive(User user) {
        return user.getActive();
    }


    @Override
    public User findUserAuthenticated() {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByUsernameOrEmail(username, username).orElse(null);

    }

}


