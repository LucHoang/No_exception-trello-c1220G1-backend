package com.example.no_exception_trello_c1220g1.service.CardTagUserService;

import com.example.no_exception_trello_c1220g1.model.Entity.Card_tagUser;
import com.example.no_exception_trello_c1220g1.repository.Card_tagUser_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardTagUserService implements ICardTagUserService{
    @Autowired
    private Card_tagUser_repo card_tagUser_repo;
    @Override
    public Card_tagUser create(Card_tagUser card_tagUser) {
        return card_tagUser_repo.save(card_tagUser);
    }
}
