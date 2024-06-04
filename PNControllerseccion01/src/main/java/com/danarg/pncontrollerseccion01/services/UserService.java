package com.danarg.pncontrollerseccion01.services;

import com.danarg.pncontrollerseccion01.domain.dtos.UserRegisterDTO;
import com.danarg.pncontrollerseccion01.domain.entities.Role;
import com.danarg.pncontrollerseccion01.domain.entities.Token;
import com.danarg.pncontrollerseccion01.domain.entities.User;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface UserService {
    User findByIdentifier(String identifier);
    User findByUsernameOrEmail(String username, String email);
    void create(UserRegisterDTO info);
    void toggleEnable(String username);
    boolean checkPassword(User user, String password);
    Token registerToken(User user) throws Exception;
    Boolean isTokenValid(User user, String token);
    void cleanTokens(User user) throws Exception;
    boolean isActive(User user);
    User findUserAuthenticated();
    void updatePassword(String identifier, String newPassword);


    //roles
    void changeRoles(String username, @NotNull List<String> role);
    Role getRoleById(String role);
    List<Role> getRoles();
}
