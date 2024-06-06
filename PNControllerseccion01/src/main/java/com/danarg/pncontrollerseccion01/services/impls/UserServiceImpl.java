package com.danarg.pncontrollerseccion01.services.impls;

import com.danarg.pncontrollerseccion01.domain.dtos.UserRegisterDTO;
import com.danarg.pncontrollerseccion01.domain.entities.Role;
import com.danarg.pncontrollerseccion01.domain.entities.Token;
import com.danarg.pncontrollerseccion01.domain.entities.User;
import com.danarg.pncontrollerseccion01.repositories.RoleRepository;
import com.danarg.pncontrollerseccion01.repositories.TokenRepository;
import com.danarg.pncontrollerseccion01.repositories.UserRepository;
import com.danarg.pncontrollerseccion01.services.UserService;
import com.danarg.pncontrollerseccion01.utils.JWTTools;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final JWTTools jwtTools;
    private final TokenRepository tokenRepository;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;



    public UserServiceImpl(UserRepository userRepository, JWTTools jwtTools, TokenRepository tokenRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.jwtTools = jwtTools;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }


    @Override
    @Transactional(rollbackOn = Exception.class)
    public void create(UserRegisterDTO info) {
        //busueda de roles por
        User user = new User();
        user.setUsername(info.getUsername());
        user.setPassword(passwordEncoder.encode(info.getPassword()));
        user.setEmail(info.getEmail());
        user.setRoles(info.getRole());
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

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void updatePassword(String identifier, String newPassword) {
        User user = findByIdentifier(identifier);
        if (user == null) {
            throw new EntityNotFoundException("User not found with identifier: " + identifier);
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public void changeRoles(String username, @NotNull List<String> role) {
        User user = userRepository.findByUsernameOrEmail(username, username).orElse(null);
        if(user == null) {
            throw new EntityNotFoundException("User not found with username: " + username);
        }

        List<Role> roles = new ArrayList<>();
        role.forEach(r -> {
            Role role1 = roleRepository.findById(r).orElse(null);
            if(role1 != null) {
                roles.add(role1);
            }
        });

        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public Role getRoleById(String role) {
        return roleRepository.findById(role).orElse(null);
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }


}


