package com.example.no_exception_trello_c1220g1.model.Entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String passWord;
    private String avatar;
    private String email;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Roles> appRole;
    private String phone;

}

