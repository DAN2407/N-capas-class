package com.danarg.pncontrollerseccion01.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserEditDTO {
    //edit user
    @NotBlank
    private String username;
    @NotBlank
    private String email;

}
