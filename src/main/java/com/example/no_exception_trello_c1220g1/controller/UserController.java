package com.example.no_exception_trello_c1220g1.controller;

import com.example.no_exception_trello_c1220g1.model.dto.UserPrinciple;
import com.example.no_exception_trello_c1220g1.model.dto.UserUpdateDto;
import com.example.no_exception_trello_c1220g1.model.entity.User;
import com.example.no_exception_trello_c1220g1.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    IUserService iUserService;

    @GetMapping("")
    public ResponseEntity<List<User>> showAll(){
        return new ResponseEntity<>(iUserService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> editUserPassword(@Valid @RequestBody UserUpdateDto userUpdateDto, @PathVariable Long id, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<User> user = iUserService.findById(id);
//        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        user.get().setAvatar(userUpdateDto.getAvatar());
        user.get().setPhone(userUpdateDto.getPhone());

        return new ResponseEntity<>(iUserService.updateUser(user.get()),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id){
        return new ResponseEntity<>(iUserService.findById(id).get(),HttpStatus.OK);
    }
    @GetMapping("/listuseringroup/{groupid}")
    public ResponseEntity<List<User>> showAllUserInGroup(@PathVariable Long groupid){

        return new ResponseEntity<>(iUserService.findAllUserByGroup(groupid),HttpStatus.OK);
    }
//    @GetMapping("view/{id}")
//    public ResponseEntity<?> viewUserById(@PathVariable Long id){
//        User user = iUserService.findById(id).get();
//        UserUpdateDto userUpdateDto = UserUpdateDto.builder()
//                .id(user.getId())
//                .avatar(user.getAvatar())
//                .email(user.getEmail())
//                .userName(user.getUserName())
//                .passWord(user.getPassWord())
//                .phone(user.getPhone())
//                .newPassWord("")
//                .oldPassWord("")
//                .build();
//        return new ResponseEntity<>(userUpdateDto,HttpStatus.OK);
//    }
}
