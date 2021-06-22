package com.example.no_exception_trello_c1220g1.model.dto;

import com.example.no_exception_trello_c1220g1.model.entity.Comment;
import com.example.no_exception_trello_c1220g1.model.entity.ListTrello;
import lombok.Builder;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.List;

@Builder
@Data
public class CardEditDto {
    private Long id;
    private String title;
    private String content;
    private int position;
    private Long listTrelloId;
    private List<Comment> comments;
    private List<CardUserDto> cardUserDtos;
}
