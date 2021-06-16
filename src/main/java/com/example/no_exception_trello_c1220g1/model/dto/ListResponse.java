package com.example.no_exception_trello_c1220g1.model.dto;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class ListResponse {
    private Long id;
    private String title;
    private int position;
    private List<CardDto> cardDtoList;
}
