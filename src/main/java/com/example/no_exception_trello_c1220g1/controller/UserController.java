package com.example.no_exception_trello_c1220g1.controller;

import com.example.no_exception_trello_c1220g1.model.dto.UserUpdateDto;
import com.example.no_exception_trello_c1220g1.model.entity.User;
import com.example.no_exception_trello_c1220g1.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    IUserService iUserService;

    @GetMapping("")
    public ResponseEntity<List<User>> showAll(){
        return new ResponseEntity<>(iUserService.findAll(), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> editUserPassword(@RequestBody UserUpdateDto userUpdateDto, @PathVariable Long id){
        Optional<User> user = iUserService.findById(id);
        if (!user.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(!userUpdateDto.getOldPassWord().equals(user.get().getPassWord())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        user.get().setPassWord(userUpdateDto.getNewPassWord());
        user.get().setAvatar(userUpdateDto.getAvatar());

        return new ResponseEntity<>(iUserService.save(user.get()),HttpStatus.OK);
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
