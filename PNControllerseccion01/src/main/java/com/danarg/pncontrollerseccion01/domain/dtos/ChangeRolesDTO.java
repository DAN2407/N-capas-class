package com.danarg.pncontrollerseccion01.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ChangeRolesDTO {
    @NotBlank
    private String identifier;
    @NotNull
    private List<String> roles;
}
