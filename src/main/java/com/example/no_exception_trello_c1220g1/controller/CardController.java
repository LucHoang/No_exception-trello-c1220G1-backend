package com.example.no_exception_trello_c1220g1.controller;

import com.example.no_exception_trello_c1220g1.model.dto.CardCreateDto;
import com.example.no_exception_trello_c1220g1.model.dto.CardDto;
import com.example.no_exception_trello_c1220g1.model.dto.UserPrinciple;
import com.example.no_exception_trello_c1220g1.model.entity.*;
import com.example.no_exception_trello_c1220g1.service.board.boardTagAppUser.IBoardTagAppUserService;
import com.example.no_exception_trello_c1220g1.service.cardService.ICardService;

import com.example.no_exception_trello_c1220g1.service.listService.IListService;
import com.example.no_exception_trello_c1220g1.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@RestController
@CrossOrigin("*")
@RequestMapping("/cards")
public class CardController {
    @Autowired
    private ICardService cardService;
    @Autowired
    private IListService listService;
    @Autowired
    private UserService userService;
    @Autowired
    private IBoardTagAppUserService boardTagAppUserService;
//    @GetMapping("/list/{id}")
//    public ResponseEntity<List<Card>> findCardsByListId(@PathVariable Long id){
//        return new ResponseEntity<>(cardService.findCardsByListId(id), HttpStatus.OK);
//    }
    @PutMapping("changePosition")
    public ResponseEntity<?> changePositionCard(@RequestBody List<Card> cards){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(userName);
        BoardTagAppUser boardTagUserCheck = boardTagAppUserService.findByBoardIdAndUserId(cards.get(0).getListTrello().getBoard().getId(), user.getId());

        if (boardTagUserCheck.getRoleUser().equals("ROLE_VIEW")) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        for (Card card:cards) {
            cardService.save(card);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("edit/{boardId}/")
    public ResponseEntity<?> editCard(@PathVariable Long boardId, @RequestBody CardDto cardDto){
        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        BoardTagAppUser boardTagAppUser = boardTagAppUserService.findByBoardIdAndUserId(boardId, user.getId());
        if(boardTagAppUser== null){
            return new ResponseEntity<>("user invalid in group",HttpStatus.NOT_FOUND);
        }
        if(boardTagAppUser.getRoleUser().equals("ROLE_VIEW")){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(cardService.editCard(cardDto),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Card> findCardById(@PathVariable Long id){
        if (!cardService.findById(id).isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cardService.findById(id).get(), HttpStatus.OK);
    }
    @PostMapping("create")
    public ResponseEntity<?> createCard(@RequestBody CardCreateDto cardCreateDto){
        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<ListTrello> listTrello = listService.findById(cardCreateDto.getListTrelloId());
        if (!listTrello.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        BoardTagAppUser boardTagUserCheck = (BoardTagAppUser) userPrinciple.getAllRole().get(listTrello.get().getBoard().getId()+"btu");
        GroupTagUser groupTagUserCheck = (GroupTagUser) userPrinciple.getAllRole().get(listTrello.get().getBoard().getGroupTrello().getId()+"gtu");
        if (listTrello.get().getBoard().getGroupTrello() == null || listTrello.get().getBoard().getType().equalsIgnoreCase("TYPE_PRIVATE")) {
            if (boardTagUserCheck == null || (!boardTagUserCheck.getRoleUser().equals("ROLE_ADMIN") && !boardTagUserCheck.getRoleUser().equals("ROLE_EDIT"))) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } else {
            if (boardTagUserCheck == null) {
                if (groupTagUserCheck == null || (!groupTagUserCheck.getRoleUser().equals("ROLE_ADMIN") && !groupTagUserCheck.getRoleUser().equals("ROLE_EDIT"))) {
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
                }
            } else {
                if (!boardTagUserCheck.getRoleUser().equals("ROLE_ADMIN") && !boardTagUserCheck.getRoleUser().equals("ROLE_EDIT")) {
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
                }
            }
        }

        int position = cardService.findCardsByListId(listTrello.get().getId()).size();
        Card card = Card.builder()
                .title(cardCreateDto.getTitle())
                .content(cardCreateDto.getContent())
                .position(position)
                .listTrello(listTrello.get())
                .build();

        return new ResponseEntity<>(cardService.save(card), HttpStatus.OK);
    }


    @GetMapping("/search-by-label/{id}")
        public ResponseEntity<?> searchCardByLabelsId (@PathVariable Long id){
        if (cardService.findCardByLabel(id).isEmpty()){
            return new ResponseEntity<>("not found",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cardService.findCardByLabel(id),HttpStatus.OK);
    }

}
