package com.example.no_exception_trello_c1220g1.service.listService;

import com.example.no_exception_trello_c1220g1.model.Entity.ListTrello;
import com.example.no_exception_trello_c1220g1.repository.IListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ListService implements IListService{
    @Autowired
    IListRepository listRepository;

//    @Override
//    public List<com.example.no_exception_trello_c1220g1.model.Entity.List> findAll() {
//        return null;
//    }

//    @Override
//    public Optional<com.example.no_exception_trello_c1220g1.model.Entity.List> findById(Long id) {
//        return Optional.empty();
//    }

    @Override
    public List<ListTrello> findAll() {
        return null;
    }

    @Override
    public Optional<ListTrello> findById(Long id) {
        return Optional.empty();
    }

    //    @Override
//    public List save(List list) {
//        return null;
//    }
//
//    @Override
//    public void delete(Long id) {
//
//    }
//    @Autowired
//    private ListRepo listRepo;
//    @Override
//    public java.util.List<List> findAll() {
//        return listRepo.findAll();
//    }
//
//    @Override
//    public List findById(Long id) {
//        return listRepo.findById(id).get();
//    }
//
    @Override
    public ListTrello save(ListTrello list) {
        return listRepository.save(list);
    }

    @Override
    public void delete(Long id) {

    }

    //
//    @Override
//    public void delete(Long id) {
//        listRepo.deleteById(id);
//
//    }
//
//    @Override
//    public void editPositionList(ArrayList<List> lists) {
//        for (int i = 0; i < lists.size(); i++) {
//            listRepo.save(lists.get(i));
//        }
//    }
//
    @Override
    public java.util.List<ListTrello> findListByBoardId(Long id) {
        return listRepository.findListByBoard_IdOrderByPosition(id);
    }
//    @Override
//    public List editTitleList(List list, Long id) {
//        list.setId(id);
//        return listRepo.save(list);
//    }

}

