package com.example.no_exception_trello_c1220g1.repository;

import com.example.no_exception_trello_c1220g1.model.Entity.Card_tagUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//Todo Thống nhất lại cách đặt tên, k dùng kiểu snake case
public interface CardTagUserRepo extends JpaRepository<Card_tagUser,Long> {
}
