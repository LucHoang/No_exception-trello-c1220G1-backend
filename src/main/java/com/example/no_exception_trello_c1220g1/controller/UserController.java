package com.example.no_exception_trello_c1220g1.controller;

import com.example.no_exception_trello_c1220g1.model.Entity.User;
import com.example.no_exception_trello_c1220g1.model.dto.UserDto;
import com.example.no_exception_trello_c1220g1.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;

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
    @PutMapping("/edit/{id}")
    public ResponseEntity<User> editUserPassword(@RequestBody User user,@PathVariable Long id){
        if(!user.getPassWord().equals(iUserService.findById(id).get().getPassWord())){
            user.setId(id);
            user.setPassWord(user.getPassWord());
        }


        return new ResponseEntity<>(iUserService.save(user),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id){
        return new ResponseEntity<>(iUserService.findById(id).get(),HttpStatus.OK);
    }
    @GetMapping("/listuseringroup/{groupid}")
    public ResponseEntity<List<User>> showAllUserInGroup(@PathVariable Long groupid){

        return new ResponseEntity<>(iUserService.findAllUserByGroup(groupid),HttpStatus.OK);
    }

}
