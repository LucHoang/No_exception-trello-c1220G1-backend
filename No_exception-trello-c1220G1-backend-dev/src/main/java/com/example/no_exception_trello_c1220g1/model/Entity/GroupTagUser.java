package com.example.no_exception_trello_c1220g1.model.entity;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper=true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupTagUser extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private GroupTrello groupTrello;
    private String roleUser;

}
