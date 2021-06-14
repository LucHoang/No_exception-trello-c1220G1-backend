package com.example.no_exception_trello_c1220g1.repository;

import com.example.no_exception_trello_c1220g1.model.Entity.Labels;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILabelsRepository extends JpaRepository<Labels,Long> {
}
