package com.foxminded.university.dao.hibernate;

import com.foxminded.university.dao.ConnectionFactory;
import com.foxminded.university.dao.ScheduleDao;
import com.foxminded.university.domain.Schedule;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ScheduleDaoImpl implements ScheduleDao {
    private static SessionFactory sessionFactory = ConnectionFactory.getSessionFactory();

    public void create(Schedule schedule) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(schedule);
        transaction.commit();
        session.close();
    }

    public Schedule findOne(Long id) {
        return sessionFactory.openSession().get(Schedule.class, id);
    }

    public Schedule update(Schedule schedule) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(schedule);
        session.getTransaction().commit();
        session.close();
        return schedule;
    }

    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Schedule schedule = session.get(Schedule.class, id);
        session.delete(schedule);
        transaction.commit();
        session.close();
    }

    public List<Schedule> findAll() {
        Session session = sessionFactory.openSession();
        List<Schedule> schedules = session.createQuery("FROM Schedule ").list();
        session.close();
        return schedules;
    }
}