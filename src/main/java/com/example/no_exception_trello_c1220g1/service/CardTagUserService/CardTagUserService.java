package com.example.no_exception_trello_c1220g1.service.CardTagUserService;

import com.example.no_exception_trello_c1220g1.model.Entity.CardTagUser;
import com.example.no_exception_trello_c1220g1.repository.ICardTagUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardTagUserService implements ICardTagUserService{
    @Autowired
    private ICardTagUserRepository ICard_tagUser_repository;
    @Override
    public CardTagUser create(CardTagUser card_tagUser) {
        return ICard_tagUser_repository.save(card_tagUser);
    }
}
