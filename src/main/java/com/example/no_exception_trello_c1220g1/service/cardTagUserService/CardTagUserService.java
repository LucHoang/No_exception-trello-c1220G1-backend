package com.example.no_exception_trello_c1220g1.service.cardTagUserService;

import com.example.no_exception_trello_c1220g1.model.dto.UserResponse;
import com.example.no_exception_trello_c1220g1.model.entity.CardTagUser;
import com.example.no_exception_trello_c1220g1.repository.ICardTagUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardTagUserService implements ICardTagUserService{
    @Autowired
    private ICardTagUserRepository ICard_tagUser_repository;
    @Override
    public CardTagUser create(CardTagUser card_tagUser) {
        return ICard_tagUser_repository.save(card_tagUser);
    }
    @Override
    public List<UserResponse> findAllByCardId(long cardId) {

        List<Object> resultSet = ICard_tagUser_repository.findAllByCardId(cardId);

        List<UserResponse> users = new ArrayList<>();
        for (Object o :resultSet) {
            UserResponse user = new UserResponse();
            user.setId(Long.parseLong(((Object[])o)[0].toString()));
            user.setAvatar(((Object[])o)[3].toString());
            user.setUsername(((Object[])o)[2].toString());
            user.setEmail(((Object[])o)[1].toString());
            users.add(user);
        }
        return users;
    }
}
