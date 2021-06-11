package com.example.no_exception_trello_c1220g1.model.Entity;

import com.example.no_exception_trello_c1220g1.model.Entity.AppUser;
import com.example.no_exception_trello_c1220g1.model.Entity.Card;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card_tagUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Card card;
    @OneToOne
    private AppUser appUser;

}
