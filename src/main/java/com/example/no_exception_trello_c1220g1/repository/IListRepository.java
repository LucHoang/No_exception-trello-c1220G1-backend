package com.example.no_exception_trello_c1220g1.repository;

import com.example.no_exception_trello_c1220g1.model.entity.ListTrello;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IListRepository extends JpaRepository<ListTrello,Long> {
    java.util.List<ListTrello> findListByBoard_IdOrderByPosition(Long id);

    @Query(value = "call getListByCardId(:cardId);", nativeQuery = true)
    Optional<ListTrello> findListByCardId(@Param("cardId") long cardId);
}
