package com.example.no_exception_trello_c1220g1.controller;

import com.example.no_exception_trello_c1220g1.model.dto.UserPrinciple;
import com.example.no_exception_trello_c1220g1.model.entity.BoardTagAppUser;
import com.example.no_exception_trello_c1220g1.model.entity.Card;
import com.example.no_exception_trello_c1220g1.model.entity.CardTagUser;
import com.example.no_exception_trello_c1220g1.model.entity.User;
import com.example.no_exception_trello_c1220g1.service.board.boardTagAppUser.BoardTagAppUserService;
import com.example.no_exception_trello_c1220g1.service.cardService.CardService;
import com.example.no_exception_trello_c1220g1.service.cardTagUserService.CardTagUserService;
import com.example.no_exception_trello_c1220g1.service.listService.ListService;
import com.example.no_exception_trello_c1220g1.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@Controller
@RequestMapping("card/tagUser")
public class CardTagUserController {
    @Autowired
    private CardTagUserService cardTagUserService;
    @Autowired
    private UserService userService;
    @Autowired
    private BoardTagAppUserService boardTagAppUserService;
    @Autowired
    private ListService listService;
    @Autowired
    private CardService cardService;
//    @GetMapping("/{id}")
//    public ResponseEntity<List<AppUser>> findListAppUserByCardId(@PathVariable Long id){
//        return new ResponseEntity<>(appUserService.findListAppUserByCardId(id), HttpStatus.OK);
//    }
//    @GetMapping("selected/{id}")
//    public ResponseEntity<List<AppUser>> findListSelected(@PathVariable Long id){
//        return new ResponseEntity<>(appUserService.findListSelected(id), HttpStatus.OK);
//    }
    @PostMapping("/addAppUserToCard")
    public ResponseEntity<?> addUserToCard(@RequestBody CardTagUser cardTagUser){
        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Card card = cardService.findById(cardTagUser.getCard().getId()).get();

        Long boardId = listService.findBoardIdByListTrelloId(card.getListTrello().getId());
        BoardTagAppUser boardTagAppUser = boardTagAppUserService.findByBoardIdAndUserId(boardId,user.getId());
        if(boardTagAppUser.getRoleUser().equals("ROLE_USER")){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        cardTagUserService.create(cardTagUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
