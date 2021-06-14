package com.example.no_exception_trello_c1220g1.service.group;

import com.example.no_exception_trello_c1220g1.model.Entity.GroupTrello;

import java.util.List;
import java.util.Optional;

public class GroupService implements IGroupService{
    @Override
    public List<GroupTrello> findAll() {
        return null;
    }

    @Override
    public Optional<GroupTrello> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public GroupTrello save(GroupTrello groupTrello) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
