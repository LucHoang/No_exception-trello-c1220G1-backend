package com.example.no_exception_trello_c1220g1.service.group.groupTagUser;

import com.example.no_exception_trello_c1220g1.model.entity.GroupTagUser;
import com.example.no_exception_trello_c1220g1.repository.IGroupTagUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class GroupTagUserService implements IGroupTagUserService{
    @Autowired
    IGroupTagUserRepository groupTagUserRepository;
    @Override
    public List<GroupTagUser> findAll() {
        return null;
    }

    @Override
    public Optional<GroupTagUser> findById(Long id) {
        return groupTagUserRepository.findById(id) ;
    }

    @Override
    public GroupTagUser findByGroupIdAndUserId(Long groupId, Long userId) {
        return groupTagUserRepository.findByGroupTrello_IdAndAndUser_Id(groupId, userId);
    }

    @Override
    public GroupTagUser save(GroupTagUser groupTagUser) {
        return groupTagUserRepository.save(groupTagUser);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<GroupTagUser> findAllByUserId(Long id) {
        return groupTagUserRepository.findAllByUserId(id);
    }

    @Override
    public Iterable<GroupTagUser> findAllByUserIdAndType(Long id, String type) {
        return groupTagUserRepository.findAllByUserIdAndType(type,id);
    }

    @Override
    public void deleteUserFromGroup(Long userId, Long groupId) {
        groupTagUserRepository.deleteByUserId(userId,groupId);
    }

    @Override
    public List<GroupTagUser> findAllByGroupTrelloId (Long id) {
        return groupTagUserRepository.findAllByGroupTrello_Id(id);
    }

}
