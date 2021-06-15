package com.example.no_exception_trello_c1220g1.controller;

import com.example.no_exception_trello_c1220g1.model.Entity.BoardTagAppUser;
import com.example.no_exception_trello_c1220g1.model.dto.BoardDto;
import com.example.no_exception_trello_c1220g1.service.board.boardTagAppUser.BoardTagAppUserService;
import com.example.no_exception_trello_c1220g1.service.token.JwtService;
import com.example.no_exception_trello_c1220g1.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/boardAppUser")
public class BoardTagAppUserController {
    @Autowired
    private BoardTagAppUserService boardTagAppUserService;
    @Autowired
    private IUserService iUserService;
    @Autowired
    private JwtService jwtService;

    @GetMapping("listboardwithtype/")
    public ResponseEntity<List<BoardDto>> getListByType( HttpServletRequest request){
                String authHeader = request.getHeader("Authorization");


        String username = jwtService.getUserNameFromJwtToken(authHeader.replace("Bearer ",""));
        Long userId = iUserService.findByUsername(username).getId();

        List<BoardTagAppUser> boardTagAppUserList = boardTagAppUserService.findBoardByUserIdAndTypeBoardAndRoleUser(userId);
        List<BoardDto> boardDtoList = new ArrayList<>();
        for (BoardTagAppUser board:boardTagAppUserList
             ) {
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
