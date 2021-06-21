package com.example.no_exception_trello_c1220g1.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {
    String username;
    String avatar;
    String content;
    String created_date;
}
