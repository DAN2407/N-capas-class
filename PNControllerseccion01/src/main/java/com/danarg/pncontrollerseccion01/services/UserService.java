package com.danarg.pncontrollerseccion01.services;

import com.danarg.pncontrollerseccion01.domain.dtos.UserEditDTO;
import com.danarg.pncontrollerseccion01.domain.dtos.UserRegisterDTO;
import com.danarg.pncontrollerseccion01.domain.entities.User;
public interface UserService {
    User findByIdentifier(String identifier);
    User findByUsernameOrEmail(String username, String email);
    void register(UserRegisterDTO userRegisterDTO);

    void editUser(UserEditDTO userEditDTO, User user);

    boolean checkPassword(User user, String password);




}
