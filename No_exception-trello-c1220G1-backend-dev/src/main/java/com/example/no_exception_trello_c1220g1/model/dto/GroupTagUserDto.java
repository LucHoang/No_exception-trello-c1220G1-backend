package com.example.no_exception_trello_c1220g1.model.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Builder
@Data
public class GroupTagUserDto {
    @NotBlank
    private Long groupId;
    @NotBlank
    @Pattern(regexp="^[a-z][a-z0-9_\\.]{5,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$")
    private String email;
    @NotBlank
    private String roleUser;
}
