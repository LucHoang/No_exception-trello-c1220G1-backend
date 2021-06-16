package com.example.no_exception_trello_c1220g1.service.group.groupTagUser;

import com.example.no_exception_trello_c1220g1.model.entity.GroupTagUser;
import com.example.no_exception_trello_c1220g1.service.IGeneralService;

public interface IGroupTagUserService extends IGeneralService<GroupTagUser> {
    GroupTagUser findByGroupIdAndUserId(Long groupId, Long userId);
    Iterable<GroupTagUser> findAllByUserId(Long id);
    Iterable<GroupTagUser> findAllByUserIdAndType(Long id, String type);

}
