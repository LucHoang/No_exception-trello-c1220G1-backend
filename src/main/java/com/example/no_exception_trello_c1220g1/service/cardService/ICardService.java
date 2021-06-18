package com.example.no_exception_trello_c1220g1.service.cardService;

import com.example.no_exception_trello_c1220g1.model.dto.CardDto;
import com.example.no_exception_trello_c1220g1.model.entity.Card;
import com.example.no_exception_trello_c1220g1.service.IGeneralService;

import java.util.List;

public interface ICardService extends IGeneralService<Card> {
    List<Card> findCardsByListId(Long id);
//    List<Card> findCardsByBroadIdAndUserId (Long broadId, Long userId);
//    List<Card> findAllCard();
    List<CardDto> findCardByLabel(Long label_id);
    Card editCard(CardDto cardDto);

}
