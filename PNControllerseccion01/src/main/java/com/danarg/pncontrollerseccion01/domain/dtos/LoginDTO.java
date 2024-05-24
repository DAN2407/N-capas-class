package com.danarg.pncontrollerseccion01.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO {
    @NotBlank
    private String identifier;
    private String password;
}
