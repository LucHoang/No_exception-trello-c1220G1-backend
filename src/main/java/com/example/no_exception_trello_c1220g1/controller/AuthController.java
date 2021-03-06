package com.example.no_exception_trello_c1220g1.controller;

import com.example.no_exception_trello_c1220g1.model.dto.LoginForm;
import com.example.no_exception_trello_c1220g1.model.entity.User;
import com.example.no_exception_trello_c1220g1.model.dto.JwtResponse;
import com.example.no_exception_trello_c1220g1.service.token.JwtService;
import com.example.no_exception_trello_c1220g1.model.dto.UserDto;
import com.example.no_exception_trello_c1220g1.service.user.IUserService;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginForm loginForm,BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userService.findByUsername(loginForm.getUsername());
        if(currentUser == null){
            currentUser = userService.findByEmail(loginForm.getUsername());
        }

        return ResponseEntity.ok(new JwtResponse(currentUser.getId(),  userDetails.getUsername(), jwt,currentUser.getAvatar(), userDetails.getAuthorities()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (userService.checkUserNameEmail(userDto.getUsername(), userDto.getEmail()).equals("nameExist")) {
            return new ResponseEntity<>("username", HttpStatus.BAD_REQUEST);
        }
        if (userService.checkUserNameEmail(userDto.getUsername(), userDto.getEmail()).equals("mailExist")) {
            return new ResponseEntity<>("email", HttpStatus.BAD_REQUEST);
        }

        User user = User.builder()
                .userName(userDto.getUsername())
                .passWord(userDto.getPassword())
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .avatar("https://firebasestorage.googleapis.com/v0/b/fir-upload-file-7f971.appspot.com/o/3gqt8ojnhr7?alt=media&token=9ca25d77-b8b4-4f36-b927-9de2bb782eb7")
                .build();
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }
}
