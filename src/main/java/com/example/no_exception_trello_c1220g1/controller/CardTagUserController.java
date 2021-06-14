package com.example.no_exception_trello_c1220g1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@Controller
@RequestMapping("card/tagUser")
public class CardTagUserController {
//    @Autowired
//    private IAppUserService appUserService;
//    @GetMapping("/{id}")
//    public ResponseEntity<List<AppUser>> findListAppUserByCardId(@PathVariable Long id){
//        return new ResponseEntity<>(appUserService.findListAppUserByCardId(id), HttpStatus.OK);
//    }
//    @GetMapping("selected/{id}")
//    public ResponseEntity<List<AppUser>> findListSelected(@PathVariable Long id){
//        return new ResponseEntity<>(appUserService.findListSelected(id), HttpStatus.OK);
//    }
//    @PostMapping("addAppUserToCard")
//    public ResponseEntity<?> addAppUserToCard(@RequestBody Card_tagUser card_tagUser){
//        appUserService.addNewAppUserToCard(card_tagUser);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
