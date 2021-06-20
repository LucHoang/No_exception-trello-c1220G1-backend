package com.example.no_exception_trello_c1220g1.model.dto;

import com.example.no_exception_trello_c1220g1.model.entity.Auditable;
import com.example.no_exception_trello_c1220g1.model.entity.Role;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(callSuper=true)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardUserDto extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String avatar;
    private String email;
}
