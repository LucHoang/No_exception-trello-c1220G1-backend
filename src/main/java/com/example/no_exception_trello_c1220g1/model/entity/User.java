package com.example.no_exception_trello_c1220g1.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
//    @Size(min = 6, max = 32)
    private String passWord;
    private String avatar;
    private String email;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> appRole;
    private String phone;

}

