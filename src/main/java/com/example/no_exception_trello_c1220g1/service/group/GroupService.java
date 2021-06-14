package com.example.no_exception_trello_c1220g1.service.group;

import com.example.no_exception_trello_c1220g1.model.Entity.Group;

import java.util.List;
import java.util.Optional;

public class GroupService implements IGroupService{
    @Override
    public List<Group> findAll() {
        return null;
    }

    @Override
    public Optional<Group> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Group save(Group group) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
