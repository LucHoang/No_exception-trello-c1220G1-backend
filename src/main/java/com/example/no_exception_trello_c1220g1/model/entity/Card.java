package com.example.no_exception_trello_c1220g1.model.entity;

import com.example.no_exception_trello_c1220g1.model.dto.CardUserDto;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper=true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Card extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int position;
    @ManyToOne
    private ListTrello listTrello;

}
