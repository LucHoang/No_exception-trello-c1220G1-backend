package com.example.no_exception_trello_c1220g1.service;

import java.util.List;

public interface IService <T> {
    List<T> findAll();
    T findById (Long id);
    T save( T t);
    void delete( Long id);
}