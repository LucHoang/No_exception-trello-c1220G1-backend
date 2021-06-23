package com.example.no_exception_trello_c1220g1.controller;

import com.example.no_exception_trello_c1220g1.model.dto.UserResponse;
import com.example.no_exception_trello_c1220g1.model.entity.Card;
import com.example.no_exception_trello_c1220g1.model.entity.CardTagUser;
import com.example.no_exception_trello_c1220g1.model.entity.User;
import com.example.no_exception_trello_c1220g1.service.cardService.ICardService;
import com.example.no_exception_trello_c1220g1.service.cardTagUserService.ICardTagUserService;
import com.example.no_exception_trello_c1220g1.service.user.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin("*")
@Controller
@RequestMapping("cardTagUser")
@AllArgsConstructor
public class CardTagUserController {

    IUserService userService;
    ICardTagUserService cardTagUserService;
    ICardService cardService;

    @PostMapping("{cardId}/tagUser")
    public ResponseEntity<?> tagUser(@PathVariable("cardId") long id, @RequestBody List<String> listUsername) {
        Optional<Card> card = cardService.findById(id);
        if (!card.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (listUsername.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<User> users = listUsername.stream()
                .map(username -> userService.findByUsername(username)).collect(Collectors.toList());
        List<UserResponse> tagUsers = users.stream().map(user -> {
                    CardTagUser tagUser = new CardTagUser();
                    tagUser.setCard(card.get());
                    tagUser.setAppUser(user);
                    User response = cardTagUserService.create(tagUser).getAppUser();
            return UserResponse.build(response);
        }).collect(Collectors.toList());
        return new ResponseEntity<>(tagUsers, HttpStatus.CREATED);
    }
}
