package com.example.no_exception_trello_c1220g1.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Data
public class UserDto {
    @Size(min = 3, max = 30)
    private String userName;
    private MultipartFile avatarMul;
    @Email
    @NotBlank
    private String email;
    private String oldPassWord;
    private String newPassWord;
    private String phone;

}

