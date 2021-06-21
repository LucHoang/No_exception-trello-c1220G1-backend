package com.example.no_exception_trello_c1220g1.model.dto;

import lombok.Data;

@Data
public class CardCreateDto {
    private Long cardId;
    private String title;
    private String content;
    private int position;
    private Long listTrelloId;
}
