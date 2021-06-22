package com.example.no_exception_trello_c1220g1.model.dto;

import com.example.no_exception_trello_c1220g1.model.entity.GroupTrello;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class BoardDto {

    private Long id;

    private String name;

    private GroupTrello groupTrello;

    private String type;

    private String roleUser;
}
