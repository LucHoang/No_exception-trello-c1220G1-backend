package com.example.no_exception_trello_c1220g1.controller;

import com.example.no_exception_trello_c1220g1.model.Entity.BoardTagAppUser;
import com.example.no_exception_trello_c1220g1.model.dto.BoardDto;
import com.example.no_exception_trello_c1220g1.service.board.boardTagAppUser.BoardTagAppUserService;
import com.example.no_exception_trello_c1220g1.service.token.JwtService;
import com.example.no_exception_trello_c1220g1.service.user.IUserService;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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

    //Todo Cho vào request, không cho email hay role lên pathVariable ntn.
    @GetMapping("add/{boardId}/{email}/{roleUser}")
    public ResponseEntity<?> add(@PathVariable Long boardId, @PathVariable String email, @PathVariable String roleUser, HttpServletRequest request){
        User userMail = userService.findByEmail(email);
        if (userMail == null) {
            return new ResponseEntity<>("Email does not exist!", HttpStatus.NOT_FOUND);
        }
        if (boardTagAppUserService.findByBoardIdAndUserId(boardId, userMail.getId()) != null) {
            return new ResponseEntity<>("User is already a member", HttpStatus.BAD_REQUEST);
        }
        //Todo dùng SecurityContextHolder.getContext().getAuthentication() để lấy thông tin userName, Chỉ xử lí token ở bước filter đầu tiên;
//        String authHeader = request.getHeader("Authorization");
//        String userName = jwtService.getUserNameFromJwtToken(authHeader.replace("Bearer ", ""));
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
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
    //Todo đặt lên endpoint không viết liền ntn. list-board-with-type
    @GetMapping("listboardwithtype/")
    public ResponseEntity<List<BoardDto>> getListByType( HttpServletRequest request){
                String authHeader = request.getHeader("Authorization");

        //Todo áp dụng Nguyên lý S trong SOLID 1 hàm chỉ làm 1 nhiệm vụ, không xử lí token ở đây,
        String username = jwtService.getUserNameFromJwtToken(authHeader.replace("Bearer ",""));
        Long userId = userService.findByUsername(username).getId();

        List<BoardTagAppUser> boardTagAppUserList = boardTagAppUserService.findBoardByUserIdAndTypeBoardAndRoleUser(userId);
        List<BoardDto> boardDtoList = new ArrayList<>();
        for (BoardTagAppUser board:boardTagAppUserList
             ) {
            //Todo không setFields Dto ở đây, đẩy xử lí trong service
            BoardDto boardDto = new BoardDto();
            boardDto.setType(board.getBoard().getType());
            boardDto.setGroupTrello(board.getBoard().getGroupTrello());
            boardDto.setRoleUser(board.getRoleUser());
            boardDto.setId(board.getBoard().getId());
            boardDto.setName(board.getBoard().getName());
            boardDtoList.add(boardDto);
        }
        return new ResponseEntity<>(boardDtoList,HttpStatus.OK);
    }

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
