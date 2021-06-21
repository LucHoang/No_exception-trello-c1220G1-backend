package com.example.no_exception_trello_c1220g1.model.entity;

import javax.persistence.*;

@Entity
public class Notification_appUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User appUser;
    @ManyToOne
    private Notification notification;
}
