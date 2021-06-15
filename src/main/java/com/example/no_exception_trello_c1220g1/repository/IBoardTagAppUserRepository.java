package com.example.no_exception_trello_c1220g1.repository;

import com.example.no_exception_trello_c1220g1.model.Entity.BoardTagAppUser;
import com.example.no_exception_trello_c1220g1.model.Entity.GroupTagUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBoardTagAppUserRepository extends JpaRepository<BoardTagAppUser,Long> {
    BoardTagAppUser findByBoard_IdAndAppUser_Id (Long boardId, Long userId);
    @Query(value = "SELECT * FROM board join board_tag_app_user on board.id = board_tag_app_user.board_id where board_tag_app_user.app_user_id = ?1 ",nativeQuery = true)
    List<BoardTagAppUser> findBoardTagAppUserByUserIdAndTypeBoardAndRoleUser(Long id);
}
