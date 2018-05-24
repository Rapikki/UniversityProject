package com.foxminded.university.dao;

import com.foxminded.university.domain.Faculty;

import java.util.List;

public interface FacultyDao extends CrudDao<Faculty,Long> {

    Faculty findByDepartmentId(long id);

    List<Faculty> findAll();
}
