package com.foxminded.university.dao.hibernate;

import com.foxminded.university.dao.ConnectionFactory;
import com.foxminded.university.dao.LectureHallDao;
import com.foxminded.university.domain.LectureHall;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class LectureHallDaoImpl implements LectureHallDao {
    private static SessionFactory sessionFactory = ConnectionFactory.getSessionFactory();

    public void create(LectureHall lectureHall) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(lectureHall);
        session.getTransaction().commit();
        session.close();
    }

    public LectureHall findOne(Long id) {
        return sessionFactory.openSession().get(LectureHall.class, id);
    }

    public LectureHall update(LectureHall lectureHall) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(lectureHall);
        session.getTransaction().commit();
        session.close();
        return lectureHall;
    }

    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        LectureHall lectureHall = session.get(LectureHall.class, id);
        session.delete(lectureHall);
        session.getTransaction().commit();
        session.close();
    }

    public List<LectureHall> findAll() {
        Session session = sessionFactory.openSession();
        List<LectureHall> lectureHalls = session.createQuery("FROM LectureHall ").list();
        session.close();
        return lectureHalls;
    }
}
