package com.danarg.pncontrollerseccion01.controllers;

import com.danarg.pncontrollerseccion01.domain.dtos.ChangePasswordDTO;
import com.danarg.pncontrollerseccion01.domain.dtos.GeneralResponse;
import com.danarg.pncontrollerseccion01.domain.entities.User;
import com.danarg.pncontrollerseccion01.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PatchMapping("/change-password")
    public ResponseEntity<?> changePassword(@AuthenticationPrincipal User user, @RequestBody ChangePasswordDTO request) {
        if (!userService.checkPassword(user, request.getOldPassword())) {
            return GeneralResponse.getResponse(HttpStatus.CONFLICT, "Current password is incorrect");
        }

        try {
            userService.updatePassword(user.getEmail(), request.getNewPassword());
            return GeneralResponse.getResponse(HttpStatus.OK, "Password changed successfully");
        } catch (Exception e) {
            return GeneralResponse.getResponse(HttpStatus.EXPECTATION_FAILED, "Could not update password");
        }
    }
}
