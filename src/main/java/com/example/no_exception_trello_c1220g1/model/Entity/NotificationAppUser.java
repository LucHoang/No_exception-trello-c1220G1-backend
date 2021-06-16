package com.example.no_exception_trello_c1220g1.model.entity;

import javax.persistence.*;

@Entity
public class NotificationAppUser extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User appUser;
    @ManyToOne
    private Notification notification;
}
