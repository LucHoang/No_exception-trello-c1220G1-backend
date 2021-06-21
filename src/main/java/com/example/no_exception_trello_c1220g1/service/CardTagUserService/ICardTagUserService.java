package com.example.no_exception_trello_c1220g1.service.cardTagUserService;

import com.example.no_exception_trello_c1220g1.model.dto.UserResponse;
import com.example.no_exception_trello_c1220g1.model.entity.CardTagUser;
import java.util.List;

public interface ICardTagUserService {
    CardTagUser create(CardTagUser card_tagUser);

    //add
    List<UserResponse> findAllByCardId(long cardId);
}
