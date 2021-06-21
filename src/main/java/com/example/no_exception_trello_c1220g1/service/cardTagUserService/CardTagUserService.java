package com.example.no_exception_trello_c1220g1.service.cardTagUserService;

import com.example.no_exception_trello_c1220g1.model.dto.CommentResponse;
import com.example.no_exception_trello_c1220g1.model.dto.UserResponse;
import com.example.no_exception_trello_c1220g1.model.entity.CardTagUser;
import com.example.no_exception_trello_c1220g1.repository.ICardTagUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class CardTagUserService implements ICardTagUserService {

    private final ICardTagUserRepository cardTagUserRepository;

    @Override
    public CardTagUser create(CardTagUser card_tagUser) {
        return cardTagUserRepository.save(card_tagUser);
    }

    //add
    @Override
    public List<UserResponse> findAllByCardId(long cardId) {

        List<Object> resultSet = cardTagUserRepository.findAllByCardId(cardId);

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
