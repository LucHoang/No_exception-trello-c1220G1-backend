package com.example.no_exception_trello_c1220g1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/comments")
public class CommentController {
//    @Autowired
//    private ICommentService commentService;
//
//    @GetMapping("")
//    public ResponseEntity<?> showAll(){
//        return new ResponseEntity<>(commentService.findAll(),HttpStatus.OK);
//    }
//    @PostMapping("/create")
//    public ResponseEntity<?> createComment (@RequestBody Comment comment){
//        return new ResponseEntity<>(commentService.save(comment),HttpStatus.OK);
//    }
//    @PutMapping("edit/{id}")
//    public ResponseEntity<?> editComment (@PathVariable Long id,@RequestBody Comment comment ){
//        comment.setId(id);
//        return new ResponseEntity<>(commentService.save(comment),HttpStatus.OK);
//    }
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity deleteComment (@PathVariable Long id){
//        commentService.delete(id);
//        return new ResponseEntity(HttpStatus.OK);
//    }
//    @GetMapping("/card/{id}")
//    public ResponseEntity<?> findAllByCard(@PathVariable Long id){
//        return new ResponseEntity<>(commentService.findAllByCard(id ),HttpStatus.OK);
//    }

}
