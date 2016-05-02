/**
 * 
 */
package com.sogeti.dao;

import java.io.Serializable;
import java.util.List;

import java.util.List;
import java.util.Map;

public interface GenericDao<T> {

    public List<T> findAll();

    T create(T t);

    void delete(Object id);

    T find(Object id);

    T update(T t);   
}