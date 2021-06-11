package com.example.no_exception_trello_c1220g1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import java.util.Collection;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private Long id;
    private String accessToken;
    private String type = "Bearer";
    private String username;
    private String email;
    private String avatar;
    private Collection<? extends GrantedAuthority> roles;

}
