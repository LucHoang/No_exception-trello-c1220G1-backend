package com.example.no_exception_trello_c1220g1.model.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Data
public class UserDto {
    @NotBlank
    private String userName;
//    private MultipartFile avatarMul;
    private String avatar;
    @Email
    @NotBlank
    private String email;
    @Size(min = 6, max = 32)
    private String passWord;
//    private String newPassWord;
    private String phone;

}

