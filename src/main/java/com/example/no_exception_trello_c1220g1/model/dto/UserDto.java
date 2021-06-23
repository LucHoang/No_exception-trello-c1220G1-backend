package com.example.no_exception_trello_c1220g1.model.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Builder
@Data
public class UserDto {
    @NotBlank
    @Pattern(regexp="^[a-zA-Z0-9][a-zA-Z0-9]{3,30}")
    private String username;
//    private MultipartFile avatarMul;
    private String avatar;
    @Email
    @NotBlank
    @Pattern(regexp="^[a-z][a-z0-9_\\.]{2,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$")
    private String email;
    @Size(min = 6, max = 32)
    private String password;
//    private String newPassWord;
    @Pattern(regexp="(84|0[3|5|7|8|9])+([0-9]{8})\\b")
    private String phone;

}

