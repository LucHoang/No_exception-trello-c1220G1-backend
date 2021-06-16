package com.example.no_exception_trello_c1220g1.controller;

import com.example.no_exception_trello_c1220g1.model.dto.BoardTagUserDto;
import com.example.no_exception_trello_c1220g1.model.entity.Board;
import com.example.no_exception_trello_c1220g1.model.entity.BoardTagAppUser;
import com.example.no_exception_trello_c1220g1.model.dto.BoardDto;
import com.example.no_exception_trello_c1220g1.model.entity.User;
import com.example.no_exception_trello_c1220g1.service.EmailService;
import com.example.no_exception_trello_c1220g1.service.board.IBoardService;
import com.example.no_exception_trello_c1220g1.service.board.boardTagAppUser.IBoardTagAppUserService;
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
@RequestMapping("/boardTagUser")
public class BoardTagAppUserController {

    @Autowired
    private IBoardTagAppUserService boardTagAppUserService;

    @Autowired
    private UserService userService;
    @Autowired
    private IBoardService boardService;
    @Autowired
    EmailService emailService;

    //Todo Cho vào request, không cho email hay role lên pathVariable ntn.
    @PostMapping("add")
    public ResponseEntity<?> add(@Valid @RequestBody BoardTagUserDto boardTagUserDto, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User userMail = userService.findByEmail(boardTagUserDto.getEmail());
        if (userMail == null) {
            return new ResponseEntity<>("Email does not exist!", HttpStatus.NOT_FOUND);
        }
        if (boardTagAppUserService.findByBoardIdAndUserId(boardTagUserDto.getBoardId(), userMail.getId()) != null) {
            return new ResponseEntity<>("User is already a member", HttpStatus.BAD_REQUEST);
        }
//        String authHeader = request.getHeader("Authorization");
//        String userName = jwtService.getUserNameFromJwtToken(authHeader.replace("Bearer ", ""));
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(userName);
        BoardTagAppUser boardTagUserCheck = boardTagAppUserService.findByBoardIdAndUserId(boardTagUserDto.getBoardId(), user.getId());

        if (!boardTagUserCheck.getRoleUser().equals("ROLE_ADMIN")) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Optional<Board> board = boardService.findById(boardTagUserDto.getBoardId());
        if (!board.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        BoardTagAppUser boardTagAppUser = BoardTagAppUser.builder()
                .appUser(userMail)
                .board(board.get())
                .roleUser(boardTagUserDto.getRoleUser())
                .build();

//        emailService.sendEmail(email);

        return new ResponseEntity<>(boardTagAppUserService.save(boardTagAppUser), HttpStatus.OK);
    }
//    @Autowired
//    private BoardTagAppUserService boardTagAppUserService;
    //Todo đặt lên endpoint không viết liền ntn. list-board-with-type
    @GetMapping("list-board-with-type/")
    public ResponseEntity<List<BoardDto>> getListByType(){


        //Todo áp dụng Nguyên lý S trong SOLID 1 hàm chỉ làm 1 nhiệm vụ, không xử lí token ở đây,

        Long userId = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getId();

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
