package com.example.no_exception_trello_c1220g1.service.board.boardTagAppUser;

import com.example.no_exception_trello_c1220g1.model.Entity.TagUser_Board;
import com.example.no_exception_trello_c1220g1.model.Entity.User;
import com.example.no_exception_trello_c1220g1.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardTagAppUserService implements IBoardTagAppUserService{
    @Autowired
    IBoardTagAppUserService boardTagAppUserService;

    @Override
    public List<TagUser_Board> findAll() {
        return boardTagAppUserService.findAll();
    }

    @Override
    public Optional<TagUser_Board> findById(Long id) {
        return boardTagAppUserService.findById(id);
    }

    @Override
    public TagUser_Board save(TagUser_Board tagUser_board) {
        return boardTagAppUserService.save(tagUser_board);
    }

    @Override
    public void delete(Long id) {
        boardTagAppUserService.delete(id);
    }

    @Autowired
    private IUserService userService;

    @Override
    public List<User> getListTagUser(Long board_id) {
        List<User> listTagUser = new ArrayList<>();
        List<User> allUsers = userService.findAll();
        List<User> listUsersByBoard = userService.findUserAndTagUserByBoard(board_id);
        for (User user: allUsers ) {
            if (!checkListContainItem(user, listUsersByBoard)) listTagUser.add(user);
        }
        return listTagUser;

    }


    public boolean checkListContainItem(User appUser, List<User> appUserList){
        for (User a: appUserList) {
            if (a.getId()==appUser.getId()) return true;
        }
        return false;
    }
}
