package com.example.no_exception_trello_c1220g1.service.list;

import com.example.no_exception_trello_c1220g1.model.Entity.List;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ListService implements IListService{
    @Override
    public java.util.List<List> findAll() {
        return null;
    }

    @Override
    public Optional<List> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List save(List list) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
