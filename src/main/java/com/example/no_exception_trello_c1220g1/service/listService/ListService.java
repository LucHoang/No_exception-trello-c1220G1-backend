package com.example.no_exception_trello_c1220g1.service.listService;

import com.example.no_exception_trello_c1220g1.model.dto.ListResponse;
import com.example.no_exception_trello_c1220g1.model.entity.Card;
import com.example.no_exception_trello_c1220g1.model.entity.ListTrello;
import com.example.no_exception_trello_c1220g1.repository.IListRepository;
import com.example.no_exception_trello_c1220g1.service.cardService.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ListService implements IListService{
    @Autowired
    IListRepository listRepository;
    @Autowired
    ICardService iCardService;



    @Override
    public List<ListTrello> findAll() {
        return listRepository.findAll();
    }

    @Override
    public Optional<ListTrello> findById(Long id) {
        return listRepository.findById(id);
    }


    @Override
    public ListTrello save(ListTrello list) {
        return listRepository.save(list);
    }

    @Override
    public void delete(Long id) {
    listRepository.delete(findById(id).get());
    }


    @Override
    public void editPositionList(ArrayList<ListTrello> lists) {
        for (int i = 0; i < lists.size(); i++) {
            listRepository.save(lists.get(i));
        }
    }

    @Override
    public java.util.List<ListTrello> findListByBoardId(Long id) {
        return listRepository.findListByBoard_IdOrderByPosition(id);
    }
    @Override
    public ListTrello editTitleList(ListTrello list, Long id) {
        list.setId(id);
        return listRepository.save(list);
    }

    @Override
    public List<ListResponse> findAllListByBoardId(Long id) {
        List<ListTrello> listTrellos = listRepository.findListByBoard_IdOrderByPosition(id);
        List<ListResponse> listResponses = new ArrayList<>();
        List<Card> cardList = new ArrayList<>();
        for (ListTrello listTrello:listTrellos
             ) {
            cardList = iCardService.findCardsByListId(listTrello.getId());
        }

        return null;
    }


}

