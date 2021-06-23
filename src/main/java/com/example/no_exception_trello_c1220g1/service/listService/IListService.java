package com.example.no_exception_trello_c1220g1.service.listService;


import com.example.no_exception_trello_c1220g1.model.dto.ListResponse;
import com.example.no_exception_trello_c1220g1.model.dto.UserPrinciple;
import com.example.no_exception_trello_c1220g1.model.entity.ListTrello;
import com.example.no_exception_trello_c1220g1.service.IGeneralService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface IListService extends IGeneralService<ListTrello> {
    void editPositionList(ArrayList<ListTrello> lists);
    java.util.List<ListTrello> findListByBoardId(Long id);
    ListTrello editTitleList(ListTrello list, Long id);
    List<ListResponse> findAllListByBoardId(Long id);
    Optional<ListTrello> findListByCardId(long id);
    boolean checkRole (UserPrinciple userPrinciple, ListTrello listTrello);
}
