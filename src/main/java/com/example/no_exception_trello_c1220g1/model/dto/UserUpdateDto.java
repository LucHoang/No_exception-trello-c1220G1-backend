package com.example.no_exception_trello_c1220g1.model.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Builder
@Data
public class UserUpdateDto {
    private Long id;
    @NotBlank
    @Pattern(regexp="^[a-zA-Z0-9][a-zA-Z0-9]{3,30}")
    private String userName;
    //    private MultipartFile avatarMul;
    private String avatar;
    @Email
    @NotBlank
    @Pattern(regexp="^[a-z][a-z0-9_\\.]{5,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$")
    private String email;
    @Size(min = 6, max = 32)
    private String passWord;
    //    private String newPassWord;
    @Pattern(regexp="(84|0[3|5|7|8|9])+([0-9]{8})\\b")
    private String phone;
    @Size(min = 6, max = 32)
    private String oldPassWord;
    @Size(min = 6, max = 32)
    private String newPassWord;
}
