package com.example.no_exception_trello_c1220g1.service.cardService;

import com.example.no_exception_trello_c1220g1.model.Entity.Card;
import com.example.no_exception_trello_c1220g1.service.IGeneralService;

import java.util.List;

public interface ICardService extends IGeneralService<Card> {
    List<Card> findCardsByListId(Long id);
//    List<Card> findCardsByBroadIdAndUserId (Long broadId, Long userId);
//    List<Card> findAllCard();
//    List<Card> findCardByLabel(Long label_id,Long board_id);

}
