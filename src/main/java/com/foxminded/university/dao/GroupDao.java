package com.foxminded.university.dao;

import com.foxminded.university.domain.Group;

import java.util.List;

public interface GroupDao extends CrudDao<Group,Long> {

    Group findByStudent(long id);

    List<Group> findAll();
}
