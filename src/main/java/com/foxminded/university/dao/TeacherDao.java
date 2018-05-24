package com.foxminded.university.dao;

import com.foxminded.university.domain.Teacher;

public interface TeacherDao extends CrudDao<Teacher,Long> {

    void addSubject(long teacherId, long subjectId);

    void removeSubject(long teacherId, long subjectId);

    void removeAllSubjects(long teacherId);
}
