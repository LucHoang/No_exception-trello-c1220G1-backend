package com.example.no_exception_trello_c1220g1.service.board;

import com.example.no_exception_trello_c1220g1.model.Entity.Board;
import com.example.no_exception_trello_c1220g1.repository.IBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService implements IBoardService{
    @Autowired
    IBoardRepository iBoardRepository;
    @Override
    public List<Board> findAll() {
        return iBoardRepository.findAll();
    }

    @Override
    public Optional<Board> findById(Long id) {
        return iBoardRepository.findById(id);
    }

    @Override
    public Board save(Board board) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Iterable<Board> findBoardByGroupId(Long id) {
        return iBoardRepository.findBoardsByGroupId(id);
        }
}
