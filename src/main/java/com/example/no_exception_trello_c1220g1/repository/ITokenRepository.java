package com.example.no_exception_trello_c1220g1.repository;

import com.example.no_exception_trello_c1220g1.model.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ITokenRepository extends JpaRepository<Token, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Token t WHERE t.name= :token")
    void removeByName(@Param("token") String token);
}
