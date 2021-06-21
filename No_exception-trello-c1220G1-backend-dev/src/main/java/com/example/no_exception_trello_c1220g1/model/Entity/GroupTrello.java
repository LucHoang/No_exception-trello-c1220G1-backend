package com.example.no_exception_trello_c1220g1.model.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper=true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupTrello extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String name;
    @NotBlank
    private String type;
    private String description;
}
