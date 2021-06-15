package com.example.no_exception_trello_c1220g1.repository;

import com.example.no_exception_trello_c1220g1.model.Entity.TagUser_Board;
import com.example.no_exception_trello_c1220g1.model.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    User findByUserName(String username);
    User findByEmail(String email);
    @Query(value = "select u from User as u join TagUser_Board as t on u.id= t.appUser.id where t.board.id = ?1")
    List<User> findTagUserByBoard(Long Board_id);

}
