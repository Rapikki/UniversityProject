package com.foxminded.university.dao;

import com.foxminded.university.domain.Department;

public interface DepartmentDao extends CrudDao<Department, Long> {

    Department findByTeacherId(long id);

    Department findBySubjectId(long id);
}
