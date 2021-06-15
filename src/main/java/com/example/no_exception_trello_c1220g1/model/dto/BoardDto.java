package com.example.no_exception_trello_c1220g1.model.dto;

import com.example.no_exception_trello_c1220g1.model.Entity.GroupTrello;
import com.example.no_exception_trello_c1220g1.model.Entity.User;



public class BoardDto {

    private Long id;

    private String name;


    private GroupTrello groupTrello;

    private String type;

    private String roleUser;

    public BoardDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public GroupTrello getGroupTrello() {
        return groupTrello;
    }

    public void setGroupTrello(GroupTrello groupTrello) {
        this.groupTrello = groupTrello;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(String roleUser) {
        this.roleUser = roleUser;
    }
}
