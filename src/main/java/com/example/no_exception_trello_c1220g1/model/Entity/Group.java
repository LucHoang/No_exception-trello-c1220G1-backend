package com.example.no_exception_trello_c1220g1.model.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Group {
    @Id
    private long id;
    private String name;

}
