package com.foxminded.university.dao.hibernate;

import com.foxminded.university.dao.ConnectionFactory;
import com.foxminded.university.dao.StudentDao;
import com.foxminded.university.domain.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDaoImpl implements StudentDao {
    private static SessionFactory sessionFactory = ConnectionFactory.getSessionFactory();

    public void create(Student student) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(student);
        session.getTransaction().commit();
        session.close();
    }

    public Student findOne(Long id) {
        return sessionFactory.openSession().get(Student.class, id);
    }

    public Student update(Student student) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(student);
        session.getTransaction().commit();
        session.close();
        return student;
    }

    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Student student = session.get(Student.class, id);
        session.delete(student);
        session.getTransaction().commit();
        session.close();
    }

    public List<Student> findAll() {
        Session session = sessionFactory.openSession();
        List<Student> students = session.createQuery("FROM Student ").list();
        session.close();
        return students;
    }

    public List<Student> findAllByGroupId(long groupId) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Student s where s.group.id = :groupId");
        query.setParameter("groupId", groupId);
        List<Student> students = query.list();
        session.close();
        return students;
    }

    public List<Student> findAllWithoutGroup() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Student s where s.group.id IS NULL");
        List<Student> students = query.list();
        session.close();
        return students;
    }
}
