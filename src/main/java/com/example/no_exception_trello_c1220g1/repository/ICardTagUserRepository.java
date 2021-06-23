package com.example.no_exception_trello_c1220g1.repository;

import com.example.no_exception_trello_c1220g1.model.entity.CardTagUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICardTagUserRepository extends JpaRepository<CardTagUser,Long> {
    @Query(value = "call getTagUserByCardId(:cardId);", nativeQuery = true)
    List<Object> findAllByCardId(@Param("cardId") Long cardId);
    

}
