package com.example.no_exception_trello_c1220g1.service.CardTagUserService;

import com.example.no_exception_trello_c1220g1.model.Entity.Card_tagUser;
import com.example.no_exception_trello_c1220g1.repository.CardTagUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardTagUserService implements ICardTagUserService{
    @Autowired
    private CardTagUserRepo card_tagUser_repo;
    @Override
    public Card_tagUser create(Card_tagUser card_tagUser) {
        return card_tagUser_repo.save(card_tagUser);
    }
}
