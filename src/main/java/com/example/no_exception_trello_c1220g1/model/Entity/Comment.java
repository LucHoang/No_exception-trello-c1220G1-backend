package com.example.no_exception_trello_c1220g1.model.Entity;

import com.example.no_exception_trello_c1220g1.model.Entity.AppUser;
import com.example.no_exception_trello_c1220g1.model.Entity.Card;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    @ManyToOne
    private AppUser appUser;
    @ManyToOne
    private Card card;
    private Date date_create;
}
