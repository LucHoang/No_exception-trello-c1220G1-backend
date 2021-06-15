package com.example.no_exception_trello_c1220g1.controller;

import com.example.no_exception_trello_c1220g1.model.Entity.BoardTagAppUser;
import com.example.no_exception_trello_c1220g1.model.Entity.GroupTagUser;
import com.example.no_exception_trello_c1220g1.model.Entity.User;
import com.example.no_exception_trello_c1220g1.service.EmailService;
import com.example.no_exception_trello_c1220g1.service.board.IBoardService;
import com.example.no_exception_trello_c1220g1.service.board.boardTagAppUser.IBoardTagAppUserService;
import com.example.no_exception_trello_c1220g1.service.group.GroupService;
import com.example.no_exception_trello_c1220g1.service.group.IGroupService;
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
@RequestMapping("/boardTagUser")
public class BoardTagAppUserController {
    @Autowired
    private IGroupTagUserService groupTagUserService;
    @Autowired
    private IBoardTagAppUserService boardTagAppUserService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;
    @Autowired
    private IBoardService boardService;
    @Autowired
    EmailService emailService;

    @GetMapping("add/{boardId}/{email}/{roleUser}")
    public ResponseEntity<?> add(@PathVariable Long boardId, @PathVariable String email, @PathVariable String roleUser, HttpServletRequest request){
        User userMail = userService.findByEmail(email);
        if (userMail == null) {
            return new ResponseEntity<>("Email does not exist!", HttpStatus.NOT_FOUND);
        }
        if (boardTagAppUserService.findByBoardIdAndUserId(boardId, userMail.getId()) != null) {
            return new ResponseEntity<>("User is already a member", HttpStatus.BAD_REQUEST);
        }

        String authHeader = request.getHeader("Authorization");
        String userName = jwtService.getUserNameFromJwtToken(authHeader.replace("Bearer ", ""));
        User user = userService.findByUsername(userName);
        BoardTagAppUser boardTagUserCheck = boardTagAppUserService.findByBoardIdAndUserId(boardId, user.getId());

        if (!boardTagUserCheck.getRoleUser().equals("ROLE_ADMIN")) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        BoardTagAppUser boardTagAppUser = BoardTagAppUser.builder()
                .appUser(userMail)
                .board(boardService.findById(boardId).get())
                .roleUser(roleUser)
                .build();

//        emailService.sendEmail(email);

        return new ResponseEntity<>(boardTagAppUserService.save(boardTagAppUser), HttpStatus.OK);
    }
//    @Autowired
//    private BoardTagAppUserService boardTagAppUserService;
//
//    @Autowired
//    private IAppUserService userService;
//
//    @GetMapping("")
//    public ResponseEntity <List<TagUser_Board>> showAll(){
//        return new ResponseEntity<>(boardTagAppUserService.findAll(),HttpStatus.OK);
//    }
//
//    @PostMapping("create")
//    public ResponseEntity<TagUser_Board> create(@RequestBody TagUser_Board tagUserBoard){
//         return new ResponseEntity<>(boardTagAppUserService.save(tagUserBoard), HttpStatus.OK);
//     }
//
//    @GetMapping("list/{id}")
//    public ResponseEntity<List<AppUser>> getListUserTag(@PathVariable Long id){
//        AppUser currentUser = userService.getUserCurrent();
//        List<AppUser> users = boardTagAppUserService.getListTagUser(id);
//        for (AppUser user: users) {
//            if (user.getId() == currentUser.getId()) users.remove(user);
//        }
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }
}
