package com.example.no_exception_trello_c1220g1.controller;

import com.example.no_exception_trello_c1220g1.model.Entity.Board;
import com.example.no_exception_trello_c1220g1.service.board.IBoardService;
import com.example.no_exception_trello_c1220g1.model.Entity.Board;
import com.example.no_exception_trello_c1220g1.model.Entity.BoardTagAppUser;
import com.example.no_exception_trello_c1220g1.model.Entity.GroupTagUser;
import com.example.no_exception_trello_c1220g1.service.board.IBoardService;
import com.example.no_exception_trello_c1220g1.service.board.boardTagAppUser.IBoardTagAppUserService;
import com.example.no_exception_trello_c1220g1.service.group.IGroupService;
import com.example.no_exception_trello_c1220g1.service.group.groupTagUser.IGroupTagUserService;
import com.example.no_exception_trello_c1220g1.service.token.JwtService;
import com.example.no_exception_trello_c1220g1.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private IBoardService boardService;

    @Autowired
    private IGroupTagUserService groupTagUserService;
    @Autowired
    private IBoardTagAppUserService boardTagAppUserService;



    @GetMapping("")
    public ResponseEntity<List<Board>> showAll() {
        return new ResponseEntity<>(boardService.findAll(), HttpStatus.OK);
    }
    @GetMapping("list-board-in-group/{id}")
    public ResponseEntity<Iterable<Board>> showListBoardInGroup(@PathVariable Long id){
        //Todo dùng cái gì đây? sao 1 hàm 2 lần gọi service vậy? listBoard để làm gì đây?

        return new ResponseEntity<>(boardService.findBoardByGroupId(id),HttpStatus.OK);
    }
//
    @PostMapping("create")
    public ResponseEntity<Board> create(@Valid @RequestBody Board board, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (board.getGroupTrello() != null) {
            GroupTagUser groupTagUserCheck = groupTagUserService.findByGroupIdAndUserId(board.getGroupTrello().getId(), board.getUser().getId());
            if (groupTagUserCheck.getRoleUser().equals("ROLE_VIEW")) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }

        BoardTagAppUser boardTagAppUser = BoardTagAppUser.builder()
                .appUser(board.getUser())
                .board(boardService.save(board))
                .roleUser("ROLE_ADMIN")
                .build();
        boardTagAppUserService.save(boardTagAppUser);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("findBoardById/{id}")
    public ResponseEntity<Board> findBoardById(@PathVariable Long id) {
        return new ResponseEntity<>(boardService.findById(id).get(), HttpStatus.OK);
    }
}
