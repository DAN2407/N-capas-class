package com.danarg.pncontrollerseccion01.services;

import com.danarg.pncontrollerseccion01.domain.dtos.ChangePasswordDTO;
import com.danarg.pncontrollerseccion01.domain.dtos.UserEditDTO;
import com.danarg.pncontrollerseccion01.domain.dtos.UserRegisterDTO;
import com.danarg.pncontrollerseccion01.domain.dtos.UserResponseDTO;
import com.danarg.pncontrollerseccion01.domain.entities.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User findByIdentifier(String identifier);

    User findByUsernameOrEmail(String username, String email);

    User findByUUID(UUID uuid);

    List<UserResponseDTO> findAll();

    void register(UserRegisterDTO userRegisterDTO);

    void changePassword(ChangePasswordDTO info);

    void deleteUser(UUID uuid);

    boolean checkPassword(User user, String password);

    void edit(UserEditDTO info);



}
