package com.example.no_exception_trello_c1220g1.controller;

import com.example.no_exception_trello_c1220g1.model.Entity.Board;
import com.example.no_exception_trello_c1220g1.service.board.IBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private IBoardService boardService;

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
//    @PostMapping("create")
//    public ResponseEntity<Board> create(@RequestBody Board board) {
//        return new ResponseEntity<>(boardService.save(board), HttpStatus.OK);
//    }
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
//    @GetMapping("findBoardById/{id}")
//    public ResponseEntity<Board> findBoardById(@PathVariable Long id) {
//        return new ResponseEntity<>(boardService.findById(id), HttpStatus.OK);
//    }
}
