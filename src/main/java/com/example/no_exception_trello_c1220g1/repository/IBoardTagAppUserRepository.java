package com.example.no_exception_trello_c1220g1.repository;

import com.example.no_exception_trello_c1220g1.model.Entity.BoardTagAppUser;
import com.example.no_exception_trello_c1220g1.model.Entity.GroupTagUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBoardTagAppUserRepository extends JpaRepository<BoardTagAppUser,Long> {
    BoardTagAppUser findByBoard_IdAndAppUser_Id (Long boardId, Long userId);
}
