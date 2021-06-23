package com.example.no_exception_trello_c1220g1.controller;

import com.example.no_exception_trello_c1220g1.model.dto.RoleUserGroupDto;
import com.example.no_exception_trello_c1220g1.model.dto.UserPrinciple;
import com.example.no_exception_trello_c1220g1.model.dto.UserResponse;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        GroupTrello group = groupService.save(groupTrello);

        GroupTagUser groupTagUser = GroupTagUser.builder()
                .user(user)
                .groupTrello(group)
                .roleUser("ROLE_ADMIN")
                .build();
        groupTagUserService.save(groupTagUser);

        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<?> editGroup(@RequestBody GroupTrello groupTrello, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(userName);
        GroupTagUser groupTagUser = groupTagUserService.findByGroupIdAndUserId(user.getId(), groupTrello.getId());
        if (!groupTagUser.getRoleUser().equals("ROLE_ADMIN")) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        groupService.save(groupTrello);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/editRoleUser")
    public ResponseEntity<?> editRoleUser(@RequestBody RoleUserGroupDto roleUserGroupDto, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        GroupTagUser groupTagUserCheck = (GroupTagUser) userPrinciple.getAllRole().get(roleUserGroupDto.getGroupId() + "gtu");

        if (groupTagUserCheck == null || !groupTagUserCheck.getRoleUser().equals("ROLE_ADMIN")) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        GroupTagUser groupTagUser = groupTagUserService.findByGroupIdAndUserId(roleUserGroupDto.getGroupId(), roleUserGroupDto.getUserId());
        if (groupTagUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        groupTagUser.setRoleUser(roleUserGroupDto.getRoleUser());
        return new ResponseEntity<>(groupTagUserService.save(groupTagUser), HttpStatus.OK);
    }

    @PutMapping("/edit-type-group/{groupid}")
    public ResponseEntity<?> editTypeGroup(@RequestBody String type, @PathVariable Long groupid) {
        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        GroupTagUser groupTagUser = (GroupTagUser) userPrinciple.getAllRole().get(groupid + "gtu");
        if (groupTagUser == null || !groupTagUser.getRoleUser().equals("ROLE_ADMIN")) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        Optional<GroupTrello> groupTrello = groupService.findById(groupid);
        if (!groupTrello.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        groupTrello.get().setType(type);
        return new ResponseEntity<>(groupService.save(groupTrello.get()), HttpStatus.OK);
    }

    @GetMapping("{boardId}/users")
    public ResponseEntity<?> getUserInGroup(@PathVariable("boardId") long boardId) {
        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = userPrinciple.getId();
        List<UserResponse> users = groupService.findUsersInGroup(boardId).stream()
                .filter((user) -> user.getId() != id).collect(Collectors.toList());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
