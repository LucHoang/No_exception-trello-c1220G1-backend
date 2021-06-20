package com.example.no_exception_trello_c1220g1.service.cardService;

import com.example.no_exception_trello_c1220g1.model.dto.CardDto;
import com.example.no_exception_trello_c1220g1.model.dto.CardEditDto;
import com.example.no_exception_trello_c1220g1.model.dto.UserPrinciple;
import com.example.no_exception_trello_c1220g1.model.entity.*;
import com.example.no_exception_trello_c1220g1.repository.ICardRepository;
import com.example.no_exception_trello_c1220g1.repository.ICardTagLabelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Override
    public boolean checkRole (UserPrinciple userPrinciple, Optional<ListTrello> listTrello) {
        BoardTagAppUser boardTagUserCheck = (BoardTagAppUser) userPrinciple.getAllRole().get(listTrello.get().getBoard().getId()+"btu");
        GroupTagUser groupTagUserCheck = (GroupTagUser) userPrinciple.getAllRole().get(listTrello.get().getBoard().getGroupTrello().getId()+"gtu");
        if (listTrello.get().getBoard().getGroupTrello() == null || listTrello.get().getBoard().getType().equalsIgnoreCase("TYPE_PRIVATE")) {
            if (boardTagUserCheck == null || (!boardTagUserCheck.getRoleUser().equals("ROLE_ADMIN") && !boardTagUserCheck.getRoleUser().equals("ROLE_EDIT"))) {
                return false;
            }
        } else {
            if (boardTagUserCheck == null) {
                if (groupTagUserCheck == null || (!groupTagUserCheck.getRoleUser().equals("ROLE_ADMIN") && !groupTagUserCheck.getRoleUser().equals("ROLE_EDIT"))) {
                    return false;
                }
            } else {
                if (!boardTagUserCheck.getRoleUser().equals("ROLE_ADMIN") && !boardTagUserCheck.getRoleUser().equals("ROLE_EDIT")) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public List<Card> findAllByTitleOrContentContaining(String name, Long boardId) {
        List<Card> cards1 = cardRepository.findAllByTitleContaining(name);
        List<Card> cards2 = cardRepository.findAllByContentContaining(name);
        for (Card card: cards1) {
            cards2.removeIf(card2 -> card.getId().equals(card2.getId()));
        }
        cards1.addAll(cards2);

        cards1.removeIf(card -> !card.getListTrello().getBoard().getId().equals(boardId));
//        Page<Card> cardPage = (Page<Card>) cards1;
        return cards1;
    }

    @Override
    public Card updateCard(CardEditDto cardEditDto, ListTrello listTrello) {
        Card card = cardRepository.findById(cardEditDto.getId()).get();
        card.setTitle(cardEditDto.getTitle());
        card.setContent(cardEditDto.getContent());
        card.setPosition(cardEditDto.getPosition());
        card.setListTrello(listTrello);
        card.setCardUserDtos(cardEditDto.getCardUserDtos());
        card.setComments(cardEditDto.getComments());

        return cardRepository.save(card);
    }
}
