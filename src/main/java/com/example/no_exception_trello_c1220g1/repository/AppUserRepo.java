package com.example.no_exception_trello_c1220g1.repository;

import com.example.no_exception_trello_c1220g1.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser,Long> {
    AppUser findByUsername(String username);
//    Optional<AppUser> findAppUserByUserName(String username);
//    @Query(nativeQuery = true, value = "select * from app_user join card_tag_user ctu on app_user.id = ctu.app_user_id where ctu.card_id = ?")
//    List<AppUser> findListAppUserByCardId(Long card_id);
//    @Query(value = "select u from AppUser as u join TagUser_Board as t on u.id= t.appUser.id where t.board.id = ?1")
//    List<AppUser> findTagUserByBoard(Long Board_id);
//    @Query(value = "select u from AppUser as u join Board as b on u.id = b.appUser.id where b.id = ?1")
//    AppUser findUserByBoard(Long board_id);
}
