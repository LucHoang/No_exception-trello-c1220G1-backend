package com.example.no_exception_trello_c1220g1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/cards")
public class CardController {
//    @Autowired
//    private ICardService cardService;
//    @GetMapping("/list/{id}")
//    public ResponseEntity<List<Card>> findCardsByListId(@PathVariable Long id){
//        return new ResponseEntity<>(cardService.findCardsByListId(id), HttpStatus.OK);
//    }
//    @PutMapping("changePosition")
//    public ResponseEntity<?> changePositionCard(@RequestBody List<Card> cards){
//        for (Card card:cards) {
//            cardService.save(card);
//        }
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//    @PutMapping("edit/{id}")
//    public ResponseEntity<?> editCard(@PathVariable Long id, @RequestBody Card card){
//        card.setId(id);
//        cardService.save(card);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//    @GetMapping("/{id}")
//    public ResponseEntity<Card> findCardById(@PathVariable Long id){
//        return new ResponseEntity<>(cardService.findById(id), HttpStatus.OK);
//    }
//    @PostMapping("create")
//    public ResponseEntity<?> createCard(@RequestBody Card card){
//        int position = cardService.findCardsByListId(card.getList().getId()).size();
//        card.setPosition(position);
//        return new ResponseEntity<>(cardService.save(card), HttpStatus.OK);
//    }
//
//    @GetMapping("/search/{id}")
//        public ResponseEntity<?> searchCardByUser (@PathVariable Long id,@RequestParam Long userId){
//        return new ResponseEntity<>(cardService.findCardsByBroadIdAndUserId(id, userId),HttpStatus.OK);
//    }
//
//    @GetMapping("")
//    public ResponseEntity<?> showAll(){
//        return new ResponseEntity<>(cardService.findAllCard(),HttpStatus.OK);
//    }
//    @GetMapping("label/{id1}")
//    public ResponseEntity<?> findCardByLabel(@PathVariable Long id1, @RequestParam Long labelId){
//        return new ResponseEntity<>(cardService.findCardByLabel(labelId,id1 ),HttpStatus.OK);
//    }

}
