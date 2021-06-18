package com.example.no_exception_trello_c1220g1.controller;

import com.example.no_exception_trello_c1220g1.model.entity.GroupTagUser;
import com.example.no_exception_trello_c1220g1.model.entity.GroupTrello;
import com.example.no_exception_trello_c1220g1.model.entity.User;
import com.example.no_exception_trello_c1220g1.service.group.IGroupService;
import com.example.no_exception_trello_c1220g1.service.group.groupTagUser.IGroupTagUserService;
import com.example.no_exception_trello_c1220g1.service.token.JwtService;
import com.example.no_exception_trello_c1220g1.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private IGroupService groupService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IGroupTagUserService groupTagUserService;
    @Autowired
    private JwtService jwtService;

    @PostMapping("create")
    public ResponseEntity<GroupTrello> create(@Valid @RequestBody GroupTrello groupTrello, HttpServletRequest request, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
//        String authHeader = request.getHeader("Authorization");
//        String userName = jwtService.getUserNameFromJwtToken(authHeader.replace("Bearer ", ""));
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(userName);

        GroupTagUser groupTagUser = GroupTagUser.builder()
                .user(user)
                .groupTrello(groupService.save(groupTrello))
                .roleUser("ROLE_ADMIN")
                .build();
        groupTagUserService.save(groupTagUser);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/edit")
    public ResponseEntity<?> editGroup(@RequestBody GroupTrello groupTrello,BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(userName);
        GroupTagUser groupTagUser = groupTagUserService.findByGroupIdAndUserId(user.getId(),groupTrello.getId());
        if(!groupTagUser.getRoleUser().equals("ROLE_ADMIN")){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        groupService.save(groupTrello);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
