package com.example.no_exception_trello_c1220g1.service.group;

import com.example.no_exception_trello_c1220g1.model.dto.CommentResponse;
import com.example.no_exception_trello_c1220g1.model.dto.UserResponse;
import com.example.no_exception_trello_c1220g1.model.entity.GroupTrello;
import com.example.no_exception_trello_c1220g1.repository.IGroupRepository;
import com.example.no_exception_trello_c1220g1.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService implements IGroupService{
    @Autowired
    IGroupRepository groupRepository;
    @Autowired
    IUserService iUserService;

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

    @Override
    public List<UserResponse> findUsersInGroup(long boardId) {
        List<Object> resultSet = groupRepository.findUsersInGroup(boardId);

        List<UserResponse> users = new ArrayList<>();
        for (Object o :resultSet) {
            UserResponse user = new UserResponse();
            user.setUsername(((Object[])o)[1].toString());
            user.setAvatar(((Object[])o)[2].toString());
            user.setId(Long.parseLong(((Object[])o)[0].toString()));
            user.setEmail(((Object[])o)[3].toString());
            users.add(user);
        }
        return users;
    }
}
