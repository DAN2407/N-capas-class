package com.danarg.pncontrollerseccion01.domain.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SaveBookDTO {
    @NotEmpty
    @Pattern(regexp = "^\\d{9}-\\d$")
    private String ISBN;
    @NotEmpty
    private String title;
    @NotEmpty
    @Pattern(regexp = "^CT_[A-Z]{3}$")
    private String category;

}
