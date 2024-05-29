package com.danarg.pncontrollerseccion01.services;

import com.danarg.pncontrollerseccion01.domain.dtos.ChangePasswordDTO;
import com.danarg.pncontrollerseccion01.domain.dtos.UserEditDTO;
import com.danarg.pncontrollerseccion01.domain.dtos.UserRegisterDTO;
import com.danarg.pncontrollerseccion01.domain.dtos.UserResponseDTO;
import com.danarg.pncontrollerseccion01.domain.entities.Token;
import com.danarg.pncontrollerseccion01.domain.entities.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User findByIdentifier(String identifier);
    User findByUsernameOrEmail(String username, String email);
    void create(UserRegisterDTO info);
    void toggleEnable(String username);
    boolean checkPassword(User user, String password);
    void edit(UserEditDTO info);
    Token registerToken(User user) throws Exception;
    Boolean isTokenValid(User user, String token);
    void cleanTokens(User user) throws Exception;
    boolean isActive(User user);


    //Find User authenticated
    User findUserAuthenticated();
}
