package com.example.no_exception_trello_c1220g1.service.board.boardTagAppUser;

import com.example.no_exception_trello_c1220g1.model.entity.BoardTagAppUser;
import com.example.no_exception_trello_c1220g1.repository.IBoardTagAppUserRepository;

import com.example.no_exception_trello_c1220g1.model.entity.User;
import com.example.no_exception_trello_c1220g1.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardTagAppUserService implements IBoardTagAppUserService{
    @Autowired
    IBoardTagAppUserRepository boardTagAppUserRepository;

    @Override
    public BoardTagAppUser findByBoardIdAndUserId(Long boardId, Long userId) {
        return boardTagAppUserRepository.findByBoard_IdAndAppUser_Id(boardId, userId);
    }

    @Override
    public List<BoardTagAppUser> findAll() {
        return null;
    }

    @Override
    public Optional<BoardTagAppUser> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public BoardTagAppUser save(BoardTagAppUser boardTagAppUser) {
        return boardTagAppUserRepository.save(boardTagAppUser);
    }

    @Override
    public void delete(Long id) {

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

    @Override
    public List<BoardTagAppUser> findBoardByUserIdAndTypeBoardAndRoleUser(Long id) {

        return boardTagAppUserRepository.findBoardTagAppUserByUserIdAndTypeBoardAndRoleUser(id);
    }


    public boolean checkListContainItem(User appUser, List<User> appUserList){
        for (User a: appUserList) {
            if (a.getId()==appUser.getId()) return true;
        }
        return false;
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
