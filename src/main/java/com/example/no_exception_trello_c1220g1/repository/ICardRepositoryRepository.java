package com.example.no_exception_trello_c1220g1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICardRepositoryRepository extends JpaRepository<ICardRepositoryRepository,Long> {
}
