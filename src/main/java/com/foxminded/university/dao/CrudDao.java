package com.foxminded.university.dao;

import java.io.Serializable;
import java.util.List;

public interface CrudDao<T, Id extends Serializable> {

    void create(T t);

    T update(T t);

    T findOne(Id id);

    void delete(Id id);

    List<T> findAll();
}
