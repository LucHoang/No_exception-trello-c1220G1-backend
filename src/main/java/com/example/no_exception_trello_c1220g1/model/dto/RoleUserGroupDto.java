package com.example.no_exception_trello_c1220g1.model.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Builder
@Data
public class RoleUserGroupDto {
    @NotBlank
    private Long groupId;
    @NotBlank
    private Long userId;
    @NotBlank
    private String roleUser;
}
