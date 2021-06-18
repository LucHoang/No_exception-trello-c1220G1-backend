package com.example.no_exception_trello_c1220g1.service.cardService;

import com.example.no_exception_trello_c1220g1.model.dto.CardDto;
import com.example.no_exception_trello_c1220g1.model.entity.Card;
import com.example.no_exception_trello_c1220g1.model.entity.CardTagLabels;
import com.example.no_exception_trello_c1220g1.repository.ICardRepository;
import com.example.no_exception_trello_c1220g1.repository.ICardTagLabelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CardService implements ICardService {
    @Autowired
    ICardRepository cardRepository;
    @Autowired
    ICardTagLabelsRepository cardTagLabelsRepository;
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

    @Override
    public List<CardDto> findCardByLabel(Long label_id) {
        List<Card> cardList = new ArrayList<>();
        for (CardTagLabels cardTag: cardTagLabelsRepository.findByLabelId(label_id)
             ) {
            cardList.add(cardTag.getCard());
        }
        List<CardDto> cardDtoList = new ArrayList<>();
        for (Card card : cardList){
            cardDtoList.add(new CardDto(card.getId(),card.getTitle(),card.getContent(),card.getPosition()));
        }
        return cardDtoList;
    }

    @Override
    public Card editCard(CardDto cardDto) {
        Card card = cardRepository.findById(cardDto.getId()).get();
        card.setTitle(cardDto.getTitle());
        card.setListTrello(cardRepository.findById(cardDto.getId()).get().getListTrello());

        return cardRepository.save(card);
    }

}
