package com.example.no_exception_trello_c1220g1.repository;

import com.example.no_exception_trello_c1220g1.model.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface IBoardRepository extends JpaRepository<Board,Long> {
    @Query(value = "select * from cms.board where group_id_id = ?1",nativeQuery = true)
    Iterable<Board> findBoardsByGroupId(Long id);

}
