package com.example.no_exception_trello_c1220g1.controller;

import com.example.no_exception_trello_c1220g1.model.dto.GroupTagUserDto;
import com.example.no_exception_trello_c1220g1.model.dto.UserPrinciple;
import com.example.no_exception_trello_c1220g1.model.entity.GroupTagUser;
import com.example.no_exception_trello_c1220g1.model.entity.GroupTrello;
import com.example.no_exception_trello_c1220g1.model.entity.User;
import com.example.no_exception_trello_c1220g1.service.EmailService;
import com.example.no_exception_trello_c1220g1.service.group.GroupService;
import com.example.no_exception_trello_c1220g1.service.group.groupTagUser.IGroupTagUserService;
import com.example.no_exception_trello_c1220g1.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/groupTagUser")
public class GroupTagUserController {
    @Autowired
    IGroupTagUserService groupTagUserService;
    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;
    @Autowired
    EmailService emailService;

    @PostMapping("add")
    public ResponseEntity<?> add(@Valid @RequestBody GroupTagUserDto groupTagUserDto, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>("exist", HttpStatus.BAD_REQUEST);
        }

        User userMail = userService.findByEmail(groupTagUserDto.getEmail());
        if (userMail == null) {
            return new ResponseEntity<>("exist", HttpStatus.NOT_FOUND);
        }
        if (groupTagUserService.findByGroupIdAndUserId(groupTagUserDto.getGroupId(), userMail.getId()) != null) {
            return new ResponseEntity<>("already", HttpStatus.BAD_REQUEST);
        }

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(userName);
        GroupTagUser groupTagUserCheck = groupTagUserService.findByGroupIdAndUserId(groupTagUserDto.getGroupId(), user.getId());

        if (!groupTagUserCheck.getRoleUser().equals("ROLE_ADMIN")) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Optional<GroupTrello> groupTrello = groupService.findById(groupTagUserDto.getGroupId());
        if (!groupTrello.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        GroupTagUser groupTagUser = GroupTagUser.builder()
                .user(userMail)
                .groupTrello(groupTrello.get())
                .roleUser(groupTagUserDto.getRoleUser())
                .build();

//        emailService.sendEmail(groupTagUserDto.getEmail());

         return new ResponseEntity<>(groupTagUserService.save(groupTagUser), HttpStatus.OK);
     }

    @GetMapping("/listgroup/{id}")
    public ResponseEntity<Iterable<GroupTagUser>> findAllByUserId(@PathVariable Long id, HttpServletRequest request){


        Iterable<GroupTagUser> groupTagUsers = groupTagUserService.findAllByUserId(id);
        return new ResponseEntity<>(groupTagUsers,HttpStatus.OK);
    }
    @GetMapping("/listgroup/{id}/{type}")
    public ResponseEntity<Iterable<GroupTagUser>> findAllByUserIdAndType(@PathVariable Long id,@PathVariable String type){
        return new ResponseEntity<>(groupTagUserService.findAllByUserIdAndType(id,type),HttpStatus.OK);
    }
    @DeleteMapping("/deleteUser/{groupId}/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long groupId,@PathVariable Long userId){
        GroupTagUser groupTagUser = groupTagUserService.findByGroupIdAndUserId(groupId,userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getId());
        if(!groupTagUser.getRoleUser().equals("ROLE_ADMIN")){
            return new ResponseEntity<>("User has not authorities",HttpStatus.FORBIDDEN);
        }
        User user = groupTagUserService.findById(groupId).get().getUser();
        if(user== null){
            return new ResponseEntity<>("User not in Group",HttpStatus.NOT_FOUND);
        }
        groupTagUserService.deleteUserFromGroup(userId,groupId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/listUser/{id}")
    public ResponseEntity<List<GroupTagUser>> findAllByGroupId(@PathVariable Long id, HttpServletRequest request){
        List<GroupTagUser> groupTagUsers = groupTagUserService.findAllByGroupTrelloId(id);
//        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        groupTagUsers.removeIf(groupTagUser -> groupTagUser.getUser().getUserName().equalsIgnoreCase(userPrinciple.getUsername()));
//        List<User> users = null;
//        for (GroupTagUser groupTagUser: groupTagUsers) {
//            users.add(groupTagUser.getUser());
//        }
        return new ResponseEntity<>(groupTagUsers,HttpStatus.OK);
    }

}
