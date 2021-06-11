package com.example.no_exception_trello_c1220g1.service.userService;

import com.example.no_exception_trello_c1220g1.model.AppUser;
import com.example.no_exception_trello_c1220g1.model.Card_tagUser;
import com.example.no_exception_trello_c1220g1.service.IService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface IAppUserService extends IService<AppUser>, UserDetailsService {
//    Optional<AppUser> findByUsername(String username);
    AppUser findByUsername(String username);
//    List<AppUser> findListAppUserByCardId(Long card_id);
//    List<AppUser> findListSelected(Long card_id);
//    void addNewAppUserToCard(Card_tagUser cardTagUser);
//    AppUser getUserCurrent();
//    List<AppUser> findUserAndTagUserByBoard(Long board_id);
}
