package com.example.no_exception_trello_c1220g1.repository;

import com.example.no_exception_trello_c1220g1.model.entity.ListTrello;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IListRepository extends JpaRepository<ListTrello,Long> {
    java.util.List<ListTrello> findListByBoard_IdOrderByPosition(Long id);
    @Query(value = "select board_id from list_trello where id=?1",nativeQuery = true)
    Long findBoardIdByListTrelloId(Long id);
}
