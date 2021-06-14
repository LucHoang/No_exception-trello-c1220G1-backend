package com.example.no_exception_trello_c1220g1.service.board.boardTagAppUser;

import com.example.no_exception_trello_c1220g1.model.Entity.TagUser_Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardTagAppUserService implements IBoardTagAppUserService{
    @Override
    public List<TagUser_Board> findAll() {
        return null;
    }

    @Override
    public Optional<TagUser_Board> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public TagUser_Board save(TagUser_Board tagUser_board) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
//    @Autowired
//    private TagUser_Board_Repo tagUserBoardRepo;
//
//    @Autowired
//    private IAppUserService userService;
//
//    @Override
//    public List<TagUser_Board> findAll() {
//        return tagUserBoardRepo.findAll();
//    }
//
//    @Override
//    public TagUser_Board findById(Long id) {
//        return null;
//    }
//
//    @Override
//    public TagUser_Board save(TagUser_Board tagUser_board) {
//        return tagUserBoardRepo.save(tagUser_board);
//    }
//    @Override
//    public void delete(Long id) {
//    }
//
//    @Override
//    public List<AppUser> getListTagUser(Long board_id) {
//        List<AppUser> listTagUser = new ArrayList<>();
//        List<AppUser> allUsers = userService.findAll();
//        List<AppUser> listUsersByBoard = userService.findUserAndTagUserByBoard(board_id);
//        for (AppUser user: allUsers ) {
//            if (!checkListContainItem(user, listUsersByBoard)) listTagUser.add(user);
//        }
//        return listTagUser;
//    }
//
//    public boolean checkListContainItem(AppUser appUser, List<AppUser> appUserList){
//        for (AppUser a: appUserList) {
//            if (a.getId()==appUser.getId()) return true;
//        }
//        return false;
//    }
}
