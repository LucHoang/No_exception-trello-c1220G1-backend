package com.example.no_exception_trello_c1220g1.service.board.boardTagAppUser;

import com.example.no_exception_trello_c1220g1.model.entity.BoardTagAppUser;
import com.example.no_exception_trello_c1220g1.model.entity.User;
import com.example.no_exception_trello_c1220g1.service.IGeneralService;

import java.util.List;

public interface IBoardTagAppUserService extends IGeneralService<BoardTagAppUser> {
    BoardTagAppUser findByBoardIdAndUserId(Long boardId, Long userId);

    //    List<AppUser> getListTagUser(Long board_id);
List<User> getListTagUser(Long board_id);
    List<BoardTagAppUser> findBoardByUserIdAndTypeBoardAndRoleUser(Long id);

    List<BoardTagAppUser> findBoardByUserIdAndBoardType(Long id, String type);
}
