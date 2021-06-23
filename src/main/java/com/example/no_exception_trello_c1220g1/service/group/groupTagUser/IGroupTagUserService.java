package com.example.no_exception_trello_c1220g1.service.group.groupTagUser;

import com.example.no_exception_trello_c1220g1.model.entity.GroupTagUser;
import com.example.no_exception_trello_c1220g1.service.IGeneralService;

import java.util.List;

public interface IGroupTagUserService extends IGeneralService<GroupTagUser> {
    GroupTagUser findByGroupIdAndUserId(Long groupId, Long userId);
    List<GroupTagUser> findAllByUserId(Long id);
    Iterable<GroupTagUser> findAllByUserIdAndType(Long id, String type);
    void deleteUserFromGroup(Long userId,Long groupId);

    List<GroupTagUser> findAllByGroupTrelloId(Long id);
}
