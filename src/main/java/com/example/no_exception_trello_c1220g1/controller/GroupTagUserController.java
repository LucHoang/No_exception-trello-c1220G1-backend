package com.example.no_exception_trello_c1220g1.controller;

import com.example.no_exception_trello_c1220g1.model.entity.GroupTagUser;
import com.example.no_exception_trello_c1220g1.model.entity.User;
import com.example.no_exception_trello_c1220g1.service.EmailService;
import com.example.no_exception_trello_c1220g1.service.group.GroupService;
import com.example.no_exception_trello_c1220g1.service.group.groupTagUser.IGroupTagUserService;
import com.example.no_exception_trello_c1220g1.service.token.JwtService;
import com.example.no_exception_trello_c1220g1.service.user.UserService;
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
    private JwtService jwtService;
    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;
    @Autowired
    EmailService emailService;

    @GetMapping("add/{groupId}/{email}/{roleUser}")
    public ResponseEntity<?> add(@PathVariable Long groupId, @PathVariable String email, @PathVariable String roleUser, HttpServletRequest request){
        User userMail = userService.findByEmail(email);
        if (userMail == null) {
            return new ResponseEntity<>("Email does not exist!", HttpStatus.NOT_FOUND);
        }
        if (groupTagUserService.findByGroupIdAndUserId(groupId, userMail.getId()) != null) {
            return new ResponseEntity<>("User is already a member", HttpStatus.BAD_REQUEST);
        }

        String authHeader = request.getHeader("Authorization");
        String userName = jwtService.getUserNameFromJwtToken(authHeader.replace("Bearer ", ""));
        User user = userService.findByUsername(userName);
        GroupTagUser groupTagUserCheck = groupTagUserService.findByGroupIdAndUserId(groupId, user.getId());

        if (!groupTagUserCheck.getRoleUser().equals("ROLE_ADMIN")) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        GroupTagUser groupTagUser = GroupTagUser.builder()
                .user(userMail)
                .groupTrello(groupService.findById(groupId).get())
                .roleUser(roleUser)
                .build();

        emailService.sendEmail(email);

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

}
