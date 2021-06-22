package com.example.no_exception_trello_c1220g1.repository;

import com.example.no_exception_trello_c1220g1.model.entity.GroupTrello;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IGroupRepository extends JpaRepository<GroupTrello,Long> {
    @Query(nativeQuery = true,value = "SELECT * FROM group_trello where created_by = ?1")
    List<GroupTrello> findAllByUser(String username);
}
