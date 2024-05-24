package com.danarg.pncontrollerseccion01.controllers;

import com.danarg.pncontrollerseccion01.domain.dtos.*;

import com.danarg.pncontrollerseccion01.domain.entities.Token;
import com.danarg.pncontrollerseccion01.domain.entities.User;
import com.danarg.pncontrollerseccion01.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@ModelAttribute @Valid UserLoginDTO info, BindingResult validations){
        User user = userService.findByIdentifier(info.getIdentifier());

        if(validations.hasErrors()){
            return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST, "Errors in the form", validations.getAllErrors());
        }
        try {
            Token token = userService.registerToken(user);
            return new ResponseEntity<>(new TokenDTO(token), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/register")
    public ResponseEntity<GeneralResponse> register (@RequestBody @Valid UserRegisterDTO info) {
        User user = userService.findByUsernameOrEmail(info.getUsername(), info.getEmail());

        if (user != null) {
            return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST, "User already exists");
        }

        userService.register(info);

        return GeneralResponse.getResponse(HttpStatus.CREATED, "User created");

    }

    //edit
    @PatchMapping("/edit")
    public ResponseEntity<GeneralResponse> edit (@RequestBody @Valid UserEditDTO info) {
        User user = userService.findByIdentifier(info.getUsername());

        if (user == null) {
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "User not found");
        }

        userService.edit(info);

        return GeneralResponse.getResponse(HttpStatus.OK, "User updated");
    }

    //delete
    @DeleteMapping("/delete/{username}")
    public ResponseEntity<GeneralResponse> delete (@PathVariable String username) {
        User user = userService.findByIdentifier(username);

        if (user == null) {
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "User not found");
        }

        userService.deleteUser(user.getId());

        return GeneralResponse.getResponse(HttpStatus.OK, "User deleted");
    }



}
