package com.example.no_exception_trello_c1220g1.model.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
public class LoginForm {
    @NotBlank
    @Pattern(regexp="^[a-zA-Z0-9][a-zA-Z0-9]{3,30}")
    private String username;
    @Size(min = 6, max = 32)
    private String password;
}
