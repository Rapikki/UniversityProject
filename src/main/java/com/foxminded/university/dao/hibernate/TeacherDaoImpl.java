package com.foxminded.university.dao.hibernate;

import com.foxminded.university.dao.ConnectionFactory;
import com.foxminded.university.dao.TeacherDao;
import com.foxminded.university.domain.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class TeacherDaoImpl implements TeacherDao {
    private static SessionFactory sessionFactory = ConnectionFactory.getSessionFactory();

    public void create(Teacher teacher) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(teacher);
        session.getTransaction().commit();
        session.close();
    }

    public Teacher findOne(Long id) {
        return sessionFactory.openSession().get(Teacher.class, id);
    }

    public Teacher update(Teacher teacher) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(teacher);
        session.getTransaction().commit();
        session.close();
        return teacher;
    }

    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Teacher teacher = session.get(Teacher.class, id);
        session.delete(teacher);
        session.getTransaction().commit();
        session.close();
    }

    public void addSubject(long teacherId, long subjectId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query q = session.createNativeQuery("insert into teacher_subject (teacher_id, subject_id) " +
                "values (:teacherId, :subjectId);");
        q.setParameter("teacherId", teacherId);
        q.setParameter("subjectId", subjectId);
        q.executeUpdate();
        transaction.commit();
        session.close();
    }

    public void removeSubject(long teacherId, long subjectId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query q = session.createNativeQuery("delete from teacher_subject " +
                "where teacher_id = :teacherId and subject_id = :subjectId");
        q.setParameter("teacherId", teacherId);
        q.setParameter("subjectId", subjectId);
        q.executeUpdate();
        transaction.commit();
        session.close();
    }

    public void removeAllSubjects(long teacherId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query q = session.createNativeQuery("delete from teacher_subject " +
                "where teacher_id = :teacherId");
        q.setParameter("teacherId", teacherId);
        q.executeUpdate();
        transaction.commit();
        session.close();
    }

    public List<Teacher> findAll() {
        Session session = sessionFactory.openSession();
        List<Teacher> teachers = session.createQuery("FROM Teacher ").list();
        session.close();
        return teachers;
    }
}
