package com.example.no_exception_trello_c1220g1.model.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupRole {

    private int id;
    private AppUser user;
    private Group group;
    private Roles role;
}
