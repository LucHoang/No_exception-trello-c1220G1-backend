package com.example.no_exception_trello_c1220g1.controller;

import com.example.no_exception_trello_c1220g1.model.Entity.User;
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

    @GetMapping("findByUserId/{id}")
    public ResponseEntity<String> findAvatarByUserId(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        return user.map(value -> new ResponseEntity<>(value.getAvatar(), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//        if (!user.isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(user.get().getAvatar(), HttpStatus.OK);
    }

//    @GetMapping("create/{id}/{avatar}")
    @GetMapping("create/{id}")
//    public ResponseEntity<User> createAvatar(@PathVariable String avatar, @PathVariable Long id) {
    public ResponseEntity<User> createAvatar(@RequestBody String avatar, @PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (!user.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.get().setAvatar(avatar);
        return new ResponseEntity<>(userService.updateUser(user.get()), HttpStatus.OK);
    }
}
