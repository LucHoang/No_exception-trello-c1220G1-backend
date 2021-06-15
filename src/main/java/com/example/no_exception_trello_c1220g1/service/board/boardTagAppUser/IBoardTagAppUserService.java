package com.example.no_exception_trello_c1220g1.service.board.boardTagAppUser;

import com.example.no_exception_trello_c1220g1.model.Entity.TagUser_Board;
import com.example.no_exception_trello_c1220g1.model.Entity.User;
import com.example.no_exception_trello_c1220g1.service.IGeneralService;

import java.util.List;

public interface IBoardTagAppUserService extends IGeneralService<TagUser_Board> {
    List<User> getListTagUser(Long board_id);
}
