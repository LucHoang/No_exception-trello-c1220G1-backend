package com.example.no_exception_trello_c1220g1.controller;

import com.example.no_exception_trello_c1220g1.model.entity.User;
import com.example.no_exception_trello_c1220g1.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/avatar")
public class AvatarController {
    @Autowired
    private IUserService userService;

    @GetMapping("create/{id}")
    public ResponseEntity<User> createAvatar(@RequestBody String avatar, @PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (!user.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.get().setAvatar(avatar);
        return new ResponseEntity<>(userService.updateUser(user.get()), HttpStatus.OK);
    }
}
