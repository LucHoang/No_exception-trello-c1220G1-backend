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
    private IGroupService groupService;
    @Autowired
    private IBoardService boardService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IGroupTagUserService groupTagUserService;
    @Autowired
    private IBoardTagAppUserService boardTagAppUserService;
    @Autowired
    private JwtService jwtService;
//
//    @GetMapping("")
//    public ResponseEntity<List<Board>> showAll() {
//        return new ResponseEntity<>(boardService.findAll(), HttpStatus.OK);
//    }


    @GetMapping("")
    public ResponseEntity<List<Board>> showAll() {
        return new ResponseEntity<>(boardService.findAll(), HttpStatus.OK);
    }
    @GetMapping("listboardingroup/{id}")
    public ResponseEntity<Iterable<Board>> showListBoardInGroup(@PathVariable Long id){
        Optional<Board> listBoard = boardService.findById(id);
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
//
//    @GetMapping("listAppBoard/{id}")
//    public ResponseEntity<List<Board>> showAllNameBoardByTagUser(@PathVariable Long id) {
//        return new ResponseEntity<>(boardService.findAllNameBoardAppUser(id), HttpStatus.OK);
//    }
//
//    @GetMapping("listBoardTagUser/{id}")
//    public ResponseEntity<List<Board>> showAllBoardTagUser(@PathVariable Long id) {
//        return new ResponseEntity<>(boardService.findAllNameByTagUserBoard(id), HttpStatus.OK);
//    }
//
    @GetMapping("findBoardById/{id}")
    public ResponseEntity<Board> findBoardById(@PathVariable Long id) {
        return new ResponseEntity<Board>(boardService.findById(id).get(), HttpStatus.OK);
    }
}
