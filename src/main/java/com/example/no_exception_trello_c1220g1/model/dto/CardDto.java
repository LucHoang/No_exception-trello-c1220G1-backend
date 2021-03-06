package com.example.no_exception_trello_c1220g1.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
public class CardDto {
    private Long id;
    private String title;
    private String content;
    private int position;
    //add
    private List<CommentResponse> comments;
    private List<UserResponse> users;
}
