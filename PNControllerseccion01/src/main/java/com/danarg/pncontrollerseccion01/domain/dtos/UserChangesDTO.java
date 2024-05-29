package com.danarg.pncontrollerseccion01.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserChangesDTO {
    @NotBlank
    private String username;
}
