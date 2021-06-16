package com.example.no_exception_trello_c1220g1.model.dto;

import com.example.no_exception_trello_c1220g1.model.Entity.GroupTrello;
import com.example.no_exception_trello_c1220g1.model.Entity.User;
import lombok.Data;


//Todo dùng lombok, thay vì viết getter, setter ntn
@Data
public class BoardDto {

    private Long id;

    private String name;


    private GroupTrello groupTrello;

    private String type;

    private String roleUser;

    public BoardDto() {
    }


}
