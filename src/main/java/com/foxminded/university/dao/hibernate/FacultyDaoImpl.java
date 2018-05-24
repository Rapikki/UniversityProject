package com.foxminded.university.dao.hibernate;

import com.foxminded.university.dao.ConnectionFactory;
import com.foxminded.university.dao.FacultyDao;
import com.foxminded.university.domain.Faculty;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class FacultyDaoImpl implements FacultyDao {
    private static SessionFactory sessionFactory = ConnectionFactory.getSessionFactory();

    public void create(Faculty faculty) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(faculty);
        session.getTransaction().commit();
        session.close();
    }

    public Faculty findOne(Long id) {
        return sessionFactory.openSession().get(Faculty.class, id);
    }

    public Faculty update(Faculty faculty) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(faculty);
        session.getTransaction().commit();
        session.close();
        return faculty;
    }

    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Faculty faculty = session.get(Faculty.class, id);
        session.delete(faculty);
        session.getTransaction().commit();
        session.close();
    }

    public List<Faculty> findAll() {
        Session session = sessionFactory.openSession();
        List<Faculty> faculties = session.createQuery("FROM Faculty ").list();
        session.close();
        return faculties;
    }

    public Faculty findByDepartmentId(long departmentId) {
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("Select f from Faculty f, Department d where d.faculty.id = f.id " +
                "and d.id = :departmentId");
        q.setParameter("departmentId", departmentId);
        Faculty faculty = (Faculty) q.setMaxResults(1).uniqueResult();
        session.close();
        return faculty;
    }
}

