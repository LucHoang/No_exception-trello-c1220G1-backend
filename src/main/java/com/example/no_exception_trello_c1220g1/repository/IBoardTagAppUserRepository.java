package com.example.no_exception_trello_c1220g1.repository;

import com.example.no_exception_trello_c1220g1.model.entity.BoardTagAppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBoardTagAppUserRepository extends JpaRepository<BoardTagAppUser,Long> {
    BoardTagAppUser findByBoard_IdAndAppUser_Id (Long boardId, Long userId);
    @Query(value = "SELECT * FROM board join board_tag_app_user on board.id = board_tag_app_user.board_id where board_tag_app_user.app_user_id = ?1 ",nativeQuery = true)
    List<BoardTagAppUser> findBoardTagAppUserByUserIdAndTypeBoardAndRoleUser(Long id);

    List<BoardTagAppUser> findAllByAppUser_IdAndBoard_Type (Long UserId, String type);
    List<BoardTagAppUser> findAllByAppUser_Id (Long UserId);
}
