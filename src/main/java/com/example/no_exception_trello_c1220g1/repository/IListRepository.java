package com.example.no_exception_trello_c1220g1.repository;

import com.example.no_exception_trello_c1220g1.model.Entity.ListTrello;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IListRepository extends JpaRepository<ListTrello,Long> {
    java.util.List<ListTrello> findListByBoard_IdOrderByPosition(Long id);
}
