package com.example.trello_project_alcohol.service.userService;


import com.example.trello_project_alcohol.model.*;
import com.example.trello_project_alcohol.repo.AppUserRepo;
import com.example.trello_project_alcohol.repo.CardRepo;
import com.example.trello_project_alcohol.repo.Card_tagUser_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IAppUserService {
    @Autowired
    private AppUserRepo appUserRepo;
    @Autowired
    private Card_tagUser_repo card_tagUser_repo;
    @Autowired
    private CardRepo cardRepo;

    @Override
    public List<AppUser> findAll() {
        return (List<AppUser>) appUserRepo.findAll();
    }

    @Override
    public AppUser findById(Long id) {
        return appUserRepo.findById(id).get();
    }

    @Override
    public AppUser save(AppUser appUser) {
        return appUserRepo.save(appUser);
    }

    @Override
    public void delete(Long id) {
        appUserRepo.deleteById(id);

    }

    @Override
    public Optional<AppUser> findByUsername(String username) {
        return appUserRepo.findAppUserByUserName(username);
    }

    @Override
    public List<AppUser> findListAppUserByCardId(Long card_id) {
        return appUserRepo.findListAppUserByCardId(card_id);
    }

    @Override
    public List<AppUser> findListSelected(Long card_id) {
        Card card = cardRepo.findById(card_id).get();
        List<AppUser> appUserList = new ArrayList<>();
        List<AppUser> allAppUser = this.findUserAndTagUserByBoard(card.getList().getBoard().getId());
        List<AppUser> cardAppUses = this.findListAppUserByCardId(card_id);
        for (AppUser appUser:allAppUser) {
            if (!checkListContainItem(appUser,cardAppUses)) appUserList.add(appUser);
        }
        return appUserList;
    }
    public boolean checkListContainItem(AppUser appUser, List<AppUser> appUserList){
        for (AppUser a: appUserList) {
            if (a.getId()==appUser.getId()) return true;
        }
        return false;
    }


    @Override
    public void addNewAppUserToCard(Card_tagUser cardTagUser) {
        card_tagUser_repo.save(cardTagUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> userOptional = findByUsername(username);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return UserPrinciple.build(userOptional.get());
    }

    @Override
    public AppUser getUserCurrent() {
        String name;
        Object ob = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (ob instanceof UserDetails){
            name = ((UserDetails)ob).getUsername();
        }
        else {
            name = ob.toString();
        }
        Optional<AppUser> appUser = this.findByUsername(name);

        return appUser.get();
    }

    @Override
    public List<AppUser> findUserAndTagUserByBoard(Long board_id) {
        List<AppUser> appUserList = appUserRepo.findTagUserByBoard(board_id);
        AppUser user = appUserRepo.findUserByBoard(board_id);
        appUserList.add(user);
        return appUserList;
    }
}
