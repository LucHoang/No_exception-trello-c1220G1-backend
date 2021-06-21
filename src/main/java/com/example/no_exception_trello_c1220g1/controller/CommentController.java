package com.example.no_exception_trello_c1220g1.controller;

import com.example.no_exception_trello_c1220g1.model.dto.CommentRequest;
import com.example.no_exception_trello_c1220g1.model.entity.Card;
import com.example.no_exception_trello_c1220g1.model.entity.Comment;
import com.example.no_exception_trello_c1220g1.model.entity.User;
import com.example.no_exception_trello_c1220g1.service.cardService.ICardService;
import com.example.no_exception_trello_c1220g1.service.commentService.ICommentService;
import com.example.no_exception_trello_c1220g1.service.user.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/comments")
@AllArgsConstructor
public class CommentController {
    ICommentService commentService;
    ICardService cardService;
    IUserService userService;

    @PostMapping()
    public ResponseEntity<Comment> create(@RequestBody CommentRequest comment) {
        Comment entity = new Comment();
        Optional<Card> card = cardService.findById(comment.getCardId());
        if (card.isPresent()) {
            String userName = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userService.findByUsername(userName);
            entity.setContent(comment.getContent());
            entity.setCard(card.get());
            entity.setAppUser(user);
            return new ResponseEntity<>(commentService.save(entity), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
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
