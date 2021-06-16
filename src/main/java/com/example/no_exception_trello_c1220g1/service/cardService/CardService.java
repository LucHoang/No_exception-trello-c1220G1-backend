package com.example.no_exception_trello_c1220g1.service.cardService;

import com.example.no_exception_trello_c1220g1.model.entity.Card;
import com.example.no_exception_trello_c1220g1.repository.ICardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService implements ICardService {
    @Autowired
    ICardRepository cardRepository;
    @Override
    public List<Card> findAll() {
        return null;
    }

    @Override
    public Optional<Card> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Card save(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public List<Card> findCardsByListId(Long id) {
        List<Card> cardList = cardRepository.findCardsByListTrello_IdOrderByPosition(id);
        return cardList;
    }

}
