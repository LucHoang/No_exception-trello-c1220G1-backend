package com.example.no_exception_trello_c1220g1.controller;

import com.example.no_exception_trello_c1220g1.model.dto.CardCreateDto;
import com.example.no_exception_trello_c1220g1.model.dto.CardEditDto;
import com.example.no_exception_trello_c1220g1.model.dto.UserPrinciple;
import com.example.no_exception_trello_c1220g1.model.entity.*;
import com.example.no_exception_trello_c1220g1.service.board.boardTagAppUser.IBoardTagAppUserService;
import com.example.no_exception_trello_c1220g1.service.cardService.ICardService;
import com.example.no_exception_trello_c1220g1.service.listService.IListService;
import com.example.no_exception_trello_c1220g1.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

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

    @PutMapping("changePosition")
    public ResponseEntity<?> changePositionCard(@RequestBody List<CardCreateDto> cards) {
        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<ListTrello> listTrello = listService.findById(cards.get(0).getListTrelloId());
        if (!listTrello.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        for (CardCreateDto update : cards) {
            Card card = Card.builder()
                    .id(update.getCardId())
                    .title(update.getTitle())
                    .content(update.getContent())
                    .position(update.getPosition())
                    .listTrello(listTrello.get())
                    .build();
            cardService.save(card);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> findCardById(@PathVariable Long id) {
        if (!cardService.findById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cardService.findById(id).get(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createCard(@RequestBody CardCreateDto cardCreateDto) {
        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<ListTrello> listTrello = listService.findById(cardCreateDto.getListTrelloId());
        if (!listTrello.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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


//    @GetMapping("/search-by-label/{id}")
//        public ResponseEntity<?> searchCardByLabelsId (@PathVariable Long id){
//        if (cardService.findCardByLabel(id).isEmpty()){
//            return new ResponseEntity<>("not found",HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(cardService.findCardByLabel(id),HttpStatus.OK);
//    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> searchCard(@PathVariable Long id, @RequestParam("q") String
            textSearch, @PageableDefault(value = 10) Pageable pageable) {

        List<Card> cards = cardService.findAllByTitleOrContentContaining(textSearch, id);

        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @PutMapping("edit")
    public ResponseEntity<?> updateCard(@RequestBody CardCreateDto update) {
        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<Card> card = cardService.findById(update.getCardId());
        if (!card.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        card.get().setContent(update.getContent());
        return new ResponseEntity<>(cardService.save(card.get()), HttpStatus.OK);
    }
}
