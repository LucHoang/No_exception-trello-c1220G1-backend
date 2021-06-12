package com.example.no_exception_trello_c1220g1.controller.User;

import com.example.no_exception_trello_c1220g1.service.token.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/v1/user")
@CrossOrigin("*")
@AllArgsConstructor
public class User {

    private final JwtService jwtService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        String token = bearerToken.replace("Bearer ", "");
        jwtService.deleteToken(token);
        return new ResponseEntity<>("Logout success", HttpStatus.NOT_FOUND);
    }
}
