package com.example.no_exception_trello_c1220g1.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper=true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListTrello extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String title;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int position;
    @ManyToOne
    private Board board;

}
