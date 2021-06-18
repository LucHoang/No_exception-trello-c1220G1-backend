package com.example.no_exception_trello_c1220g1.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@EqualsAndHashCode(callSuper=true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardTagLabels extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Card card;
    @OneToOne
    private Labels labels;

}
