package com.example.no_exception_trello_c1220g1.controller;

import com.example.no_exception_trello_c1220g1.model.Entity.GroupTagUser;
import com.example.no_exception_trello_c1220g1.model.dto.UserPrinciple;
import com.example.no_exception_trello_c1220g1.service.group.groupTagUser.IGroupTagUserService;
import com.example.no_exception_trello_c1220g1.service.token.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin("*")
@RestController
@RequestMapping("/groupTagUser")
public class GroupTagUserController {
    @Autowired
    IGroupTagUserService groupTagUserService;
    @Autowired
    JwtService jwtService;
    @GetMapping("/{id}")
    public ResponseEntity<Iterable<GroupTagUser>> findAllByUserId(@PathVariable Long id, HttpServletRequest request){
//        String authHeader = request.getHeader("Authorization");
//
//        String username = jwtService.getUserNameFromJwtToken(authHeader.replace("Bearer ",""));

        Iterable<GroupTagUser> groupTagUsers = groupTagUserService.findAllByUserId(id);
        return new ResponseEntity<>(groupTagUsers,HttpStatus.OK);
    }
    @GetMapping("/{id}/{type}")
    public ResponseEntity<Iterable<GroupTagUser>> findAllByUserIdAndType(@PathVariable Long id,@PathVariable String type){
        return new ResponseEntity<>(groupTagUserService.findAllByUserIdAndType(id,type),HttpStatus.OK);
    }
}
