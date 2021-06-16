package com.example.no_exception_trello_c1220g1.repository;

import com.example.no_exception_trello_c1220g1.model.Entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICardRepository extends JpaRepository<Card,Long> {
    List<Card> findCardsByListTrello_IdOrderByPosition(Long id);
//    @Query(nativeQuery = true,value = "SELECT * FROM trello.card where list_trello_id =:id")
//    List<Card> findAllByListTrelloId(@Param("id") Long id);
}
