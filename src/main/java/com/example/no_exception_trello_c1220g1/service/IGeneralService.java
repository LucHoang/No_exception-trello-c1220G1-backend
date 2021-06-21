package com.example.no_exception_trello_c1220g1.service;

import java.util.List;
import java.util.Optional;

public interface IGeneralService<T> {

    List<T> findAll();

    Optional<T> findById(Long id);

    T save(T t);

    void delete(Long id);
}
