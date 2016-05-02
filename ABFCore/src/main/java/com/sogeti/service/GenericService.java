package com.sogeti.service;

import java.util.List;

import com.sogeti.db.models.ResourceType;

public interface GenericService<T> {

    public List<T> findAll();

    void create(T t);

    void delete(Integer id);

    T find(Integer id);

    T update(T t);   
}
