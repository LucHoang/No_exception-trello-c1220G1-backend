package com.example.no_exception_trello_c1220g1.service.board;

import com.example.no_exception_trello_c1220g1.model.dto.BoardDto;
import com.example.no_exception_trello_c1220g1.model.entity.Board;
import com.example.no_exception_trello_c1220g1.model.entity.User;
import com.example.no_exception_trello_c1220g1.repository.IBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService implements IBoardService{
    @Autowired
    IBoardRepository boardRepository;

    @Override
    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    @Override
    public Optional<Board> findById(Long id) {
        return boardRepository.findById(id);
    }

    @Override
    public Board save(Board board) {
        return boardRepository.save(board);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Iterable<Board> findBoardByGroupId(Long id) {
        return boardRepository.findBoardsByGroupId(id);
    }

    @Override
    public List<Board> findBoardByType(Long id, String type) {
        return boardRepository.findAllByUser_IdAndType(id, type);
    }

    @Override
    public List<BoardDto> findBoardByUsername(String username) {
        List<Board> boardList = boardRepository.findBoardByCreatedByUser(username);
        List<BoardDto> boardDtos = new ArrayList<>();
        for (Board board:boardList
             ) {
            BoardDto boardDto = new BoardDto();
            boardDto.setId(board.getId());
            boardDto.setName(board.getName());
            boardDto.setType(board.getType());
            boardDto.setGroupTrello(board.getGroupTrello());
            boardDtos.add(boardDto);
        }
        return boardDtos ;
    }


}
