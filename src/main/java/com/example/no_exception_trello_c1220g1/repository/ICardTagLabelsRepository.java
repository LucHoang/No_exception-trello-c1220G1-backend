package com.example.no_exception_trello_c1220g1.repository;

import com.example.no_exception_trello_c1220g1.model.entity.Card;
import com.example.no_exception_trello_c1220g1.model.entity.CardTagLabels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICardTagLabelsRepository extends JpaRepository<CardTagLabels,Long> {
    @Query(value = "SELECT * FROM card_tag_labels where labels_id = ?1",nativeQuery = true)
    List<CardTagLabels>  findByLabelId(Long labelId);
    }

