package com.example.no_exception_trello_c1220g1.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupRole {

    private int id;
    private User user;
    private GroupTrello groupTrello;
    private Role role;
}
