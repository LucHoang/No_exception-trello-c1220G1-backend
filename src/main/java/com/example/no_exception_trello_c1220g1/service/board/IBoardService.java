package com.example.no_exception_trello_c1220g1.service.board;

import com.example.no_exception_trello_c1220g1.model.entity.Board;
import com.example.no_exception_trello_c1220g1.service.IGeneralService;

import java.util.List;

public interface IBoardService extends IGeneralService<Board> {
    Iterable<Board> findBoardByGroupId(Long id);

    List<Board> findBoardByType(Long id, String type);
}
