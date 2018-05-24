package com.foxminded.university.dao;

import com.foxminded.university.domain.LectureHall;

import java.util.List;

public interface LectureHallDao extends CrudDao<LectureHall,Long> {

    LectureHall update(LectureHall newLectureHall);

    List<LectureHall> findAll();
}
