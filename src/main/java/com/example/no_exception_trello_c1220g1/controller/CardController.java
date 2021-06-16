package com.example.no_exception_trello_c1220g1.controller;

import com.example.no_exception_trello_c1220g1.model.dto.CardDto;
import com.example.no_exception_trello_c1220g1.model.entity.BoardTagAppUser;
import com.example.no_exception_trello_c1220g1.model.entity.Card;
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

@RestController
@CrossOrigin("*")
@RequestMapping("/cards")
public class CardController {
    @Autowired
    private ICardService cardService;
    @Autowired
    private IListService listService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;
    @Autowired
    private IBoardTagAppUserService boardTagAppUserService;
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
////    }
//    @PutMapping("edit/{id}")
//    public ResponseEntity<?> editCard(@PathVariable Long id, @RequestBody CardDto cardDto){
//        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
//
//        cardDto.setId(id);
//        cardService.save(cardDto);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//    @GetMapping("/{id}")
//    public ResponseEntity<Card> findCardById(@PathVariable Long id){
//        return new ResponseEntity<>(cardService.findById(id), HttpStatus.OK);
//    }
    @PostMapping("create")
    public ResponseEntity<?> createCard(@RequestBody Card card, HttpServletRequest request){
        //Todo dùng Security
//        String authHeader = request.getHeader("Authorization");
//        String userName = jwtService.getUserNameFromJwtToken(authHeader.replace("Bearer ", ""));
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(userName);
        BoardTagAppUser boardTagUserCheck = boardTagAppUserService.findByBoardIdAndUserId(card.getListTrello().getBoard().getId(), user.getId());

        if (boardTagUserCheck.getRoleUser().equals("ROLE_VIEW")) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        int position = cardService.findCardsByListId(card.getListTrello().getId()).size();
        card.setPosition(position);
        Card newCard = cardService.save(card);


        return new ResponseEntity<>(newCard, HttpStatus.OK);
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
