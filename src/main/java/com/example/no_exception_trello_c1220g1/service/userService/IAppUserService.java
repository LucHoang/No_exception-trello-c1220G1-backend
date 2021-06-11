package com.example.trello_project_alcohol.service.userService;

import com.example.trello_project_alcohol.model.AppUser;
import com.example.trello_project_alcohol.model.Card_Labels;
import com.example.trello_project_alcohol.model.Card_tagUser;
import com.example.trello_project_alcohol.model.Labels;
import com.example.trello_project_alcohol.service.IService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface IAppUserService extends IService<AppUser>, UserDetailsService {
    Optional<AppUser> findByUsername(String username);
    List<AppUser> findListAppUserByCardId(Long card_id);
    List<AppUser> findListSelected(Long card_id);
    void addNewAppUserToCard(Card_tagUser cardTagUser);
    AppUser getUserCurrent();
    List<AppUser> findUserAndTagUserByBoard(Long board_id);
}
