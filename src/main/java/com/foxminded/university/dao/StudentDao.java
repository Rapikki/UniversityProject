package com.foxminded.university.dao;

import com.foxminded.university.domain.Student;

import java.util.List;

public interface StudentDao extends CrudDao<Student,Long> {

    List<Student> findAllByGroupId(long groupId);

    List<Student> findAllWithoutGroup();
}
