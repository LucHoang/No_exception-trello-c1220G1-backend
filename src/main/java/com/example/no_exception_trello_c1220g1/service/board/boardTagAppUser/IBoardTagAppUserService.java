package com.example.no_exception_trello_c1220g1.service.board.boardTagAppUser;

import com.example.no_exception_trello_c1220g1.model.Entity.BoardTagAppUser;
import com.example.no_exception_trello_c1220g1.model.Entity.User;
import com.example.no_exception_trello_c1220g1.service.IGeneralService;

import java.util.List;

public interface IBoardTagAppUserService extends IGeneralService<BoardTagAppUser> {
//    List<AppUser> getListTagUser(Long board_id);
List<User> getListTagUser(Long board_id);
}
