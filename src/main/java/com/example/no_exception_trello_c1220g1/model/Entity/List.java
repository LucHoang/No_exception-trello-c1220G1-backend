package com.example.no_exception_trello_c1220g1.model.Entity;

import com.example.no_exception_trello_c1220g1.model.Entity.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class List {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int position;
    @ManyToOne
    private Board board;

}
