package com.foxminded.university.dao;

import com.foxminded.university.domain.Subject;

import java.util.List;

public interface SubjectDao extends CrudDao<Subject,Long> {

    List<Subject> findSubjectsByTeacher(long teacherId);
}
