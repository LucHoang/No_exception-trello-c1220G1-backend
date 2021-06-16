package com.example.no_exception_trello_c1220g1.service.listService;


import com.example.no_exception_trello_c1220g1.model.entity.ListTrello;
import com.example.no_exception_trello_c1220g1.service.IGeneralService;

import java.util.ArrayList;

public interface IListService extends IGeneralService<ListTrello> {
    void editPositionList(ArrayList<ListTrello> lists);
    java.util.List<ListTrello> findListByBoardId(Long id);
    ListTrello editTitleList(ListTrello list, Long id);
}
