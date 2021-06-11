package com.example.no_exception_trello_c1220g1.model.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;

@Data
public class JwtResponse {

    private Long id;
    private String username;
    private String token;
    private String type = "Bearer";
    private Collection<? extends GrantedAuthority> roles;

    public JwtResponse(Long id, String username, String token, Collection<? extends GrantedAuthority> roles) {
        this.id = id;
        this.username = username;
        this.token = token;
        this.roles = roles;
    }

}
