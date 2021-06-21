package com.example.no_exception_trello_c1220g1.service.group;

import com.example.no_exception_trello_c1220g1.model.entity.GroupTrello;
import com.example.no_exception_trello_c1220g1.repository.IGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService implements IGroupService{
    @Autowired
    IGroupRepository groupRepository;

    @Override
    public List<GroupTrello> findAll() {
        return null;
    }

    @Override
    public Optional<GroupTrello> findById(Long id) {
        return groupRepository.findById(id);
    }

    @Override
    public GroupTrello save(GroupTrello groupTrello) {
        return groupRepository.save(groupTrello);
    }

    @Override
    public void delete(Long id) {

    }
}
