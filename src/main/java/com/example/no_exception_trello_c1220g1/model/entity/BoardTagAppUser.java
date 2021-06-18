package com.example.no_exception_trello_c1220g1.model.entity;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper=true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardTagAppUser extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    private User appUser;
    @ManyToOne
    private Board board;
    private String roleUser;

}
