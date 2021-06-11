package com.example.no_exception_trello_c1220g1.controller;

import com.example.no_exception_trello_c1220g1.model.Entity.User;
import com.example.no_exception_trello_c1220g1.model.dto.JwtResponse;
import com.example.no_exception_trello_c1220g1.model.dto.UserDto;
import com.example.no_exception_trello_c1220g1.service.JwtService;
import com.example.no_exception_trello_c1220g1.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassWord()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userService.findByUsername(user.getUserName());
        return ResponseEntity.ok(new JwtResponse(currentUser.getId(), userDetails.getUsername(), jwt, userDetails.getAuthorities()));
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (userService.checkUserNameEmail(userDto.getUserName(), userDto.getEmail())) {
            User user = User.builder()
                    .userName(userDto.getUserName())
                    .passWord(userDto.getPassWord())
                    .email(userDto.getEmail())
                    .phone(userDto.getPhone())
                    .build();
            return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
