package com.danarg.pncontrollerseccion01.services.impls;

import com.danarg.pncontrollerseccion01.domain.dtos.UserEditDTO;
import com.danarg.pncontrollerseccion01.domain.dtos.UserRegisterDTO;
import com.danarg.pncontrollerseccion01.domain.entities.User;
import com.danarg.pncontrollerseccion01.repositories.UserRepository;
import com.danarg.pncontrollerseccion01.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
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
        return userRepository.findByUsernameOrEmail(username, email).orElse(null);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void register(UserRegisterDTO info) {
        User user = new User();


        user.setUsername(info.getUsername());
        user.setEmail(info.getEmail());
        user.setPassword(info.getPassword());

        userRepository.save(user);
    }

    @Override
    public boolean checkPassword(User user, String password) {
        return user.getPassword().equals(password);
    }

    @Override
    public void editUser(UserEditDTO userEditDTO, User user) {
        user.setUsername(userEditDTO.getUsername());
        user.setEmail(userEditDTO.getEmail());

        userRepository.save(user);
    }
}
