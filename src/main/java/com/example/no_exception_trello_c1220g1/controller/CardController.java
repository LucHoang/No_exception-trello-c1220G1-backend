package com.example.no_exception_trello_c1220g1.controller;

import com.example.no_exception_trello_c1220g1.model.dto.CardCreateDto;
import com.example.no_exception_trello_c1220g1.model.dto.CardDto;
import com.example.no_exception_trello_c1220g1.model.dto.UserPrinciple;
import com.example.no_exception_trello_c1220g1.model.entity.BoardTagAppUser;
import com.example.no_exception_trello_c1220g1.model.entity.Card;
import com.example.no_exception_trello_c1220g1.model.entity.ListTrello;
import com.example.no_exception_trello_c1220g1.model.entity.User;
import com.example.no_exception_trello_c1220g1.service.board.boardTagAppUser.IBoardTagAppUserService;
import com.example.no_exception_trello_c1220g1.service.cardService.ICardService;
import com.example.no_exception_trello_c1220g1.service.listService.IListService;
import com.example.no_exception_trello_c1220g1.service.token.JwtService;
import com.example.no_exception_trello_c1220g1.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/cards")
public class CardController {

    @Autowired
    private ICardService cardService;
    @Autowired
    private UserService userService;
    @Autowired
    private IBoardTagAppUserService boardTagAppUserService;
    @Autowired
    private IListService listService;

    @PutMapping("changePosition")
    public ResponseEntity<?> changePositionCard(@RequestBody List<Card> cards) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(userName);
        BoardTagAppUser boardTagUserCheck = boardTagAppUserService.findByBoardIdAndUserId(cards.get(0).getListTrello().getBoard().getId(), user.getId());

        if (boardTagUserCheck.getRoleUser().equals("ROLE_VIEW")) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        for (Card card : cards) {
            cardService.save(card);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createCard(@RequestBody CardCreateDto cardCreateDto, HttpServletRequest request) {
        //Todo dùng Security
        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<ListTrello> listTrello = listService.findById(cardCreateDto.getListTrelloId());
        if (!listTrello.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

//        if (!cardService.checkRole(userPrinciple, listTrello)) {
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//        }

        int position = cardService.findCardsByListId(listTrello.get().getId()).size();
        Card card = Card.builder()
                .title(cardCreateDto.getTitle())
                .content(cardCreateDto.getContent())
                .position(position)
                .listTrello(listTrello.get())
                .build();

        return new ResponseEntity<>(cardService.save(card), HttpStatus.OK);
    }

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
