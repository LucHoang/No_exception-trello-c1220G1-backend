package com.example.no_exception_trello_c1220g1.service.group;

import com.example.no_exception_trello_c1220g1.model.dto.UserResponse;
import com.example.no_exception_trello_c1220g1.model.entity.GroupTrello;
import com.example.no_exception_trello_c1220g1.service.IGeneralService;
import java.util.List;

public interface IGroupService extends IGeneralService<GroupTrello> {

    List<UserResponse> findUsersInGroup(long boardId);

}
