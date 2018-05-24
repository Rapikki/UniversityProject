package com.foxminded.university.dao.hibernate;

import com.foxminded.university.dao.ConnectionFactory;
import com.foxminded.university.dao.SubjectDao;
import com.foxminded.university.domain.Subject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class SubjectDaoImpl implements SubjectDao {
    private static SessionFactory sessionFactory = ConnectionFactory.getSessionFactory();

    public void create(Subject subject) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(subject);
        session.getTransaction().commit();
        session.close();
    }

    public Subject findOne(Long id) {
        return sessionFactory.openSession().get(Subject.class, id);
    }

    public Subject update(Subject subject) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(subject);
        session.getTransaction().commit();
        session.close();
        return subject;
    }

    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Subject subject = session.get(Subject.class, id);
        session.delete(subject);
        session.getTransaction().commit();
        session.close();
    }

    public List<Subject> findSubjectsByTeacher(long teacherId) {
        Session session = sessionFactory.openSession();
        Query q = session.createNativeQuery("select subject.*  from subject " +
                "inner join teacher_subject  ON (subject.id = teacher_subject.subject_id)" +
                " where teacher_id = ?1 ", Subject.class);
        q.setParameter(1, teacherId);
        List<Subject> subjects = q.getResultList();
        session.close();
        return subjects;
    }

    public List<Subject> findAll() {
        Session session = sessionFactory.openSession();
        List<Subject> subjects = session.createQuery("FROM Subject ").list();
        session.close();
        return subjects;
    }
}
