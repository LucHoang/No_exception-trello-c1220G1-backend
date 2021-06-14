package com.example.no_exception_trello_c1220g1.model.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class GroupTagUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    private User user;
    @ManyToOne
    private GroupTrello groupTrello;

    private String roleUser;

}
