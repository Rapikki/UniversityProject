package com.foxminded.university.dao.hibernate;

import com.foxminded.university.dao.ConnectionFactory;
import com.foxminded.university.dao.DepartmentDao;
import com.foxminded.university.domain.Department;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class DepartmentDaoImpl implements DepartmentDao {
    private static SessionFactory sessionFactory = ConnectionFactory.getSessionFactory();

    public void create(Department department) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(department);
        session.getTransaction().commit();
        session.close();
    }

    public Department findOne(Long id) {
        return sessionFactory.openSession().get(Department.class, id);
    }

    public Department update(Department department) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(department);
        session.getTransaction().commit();
        session.close();
        return department;
    }

    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Department department = session.get(Department.class, id);
        session.delete(department);
        session.getTransaction().commit();
        session.close();
    }

    public List<Department> findAll() {
        Session session = sessionFactory.openSession();
        List<Department> departments = session.createQuery("FROM Department").list();
        session.close();
        return departments;
    }

    public Department findBySubjectId(long subjectId) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("Select d from Department d, Subject s where s.department.id = d.id " +
                "and s.id = :subjectId");
        query.setParameter("subjectId", subjectId);
        Department department = (Department) query.setMaxResults(1).uniqueResult();
        session.close();
        return department;
    }

    public Department findByTeacherId(long teacherId) {
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("Select d from Department d, Teacher t where t.department.id = d.id " +
                "and t.id = :teacherId");
        q.setParameter("teacherId", teacherId);
        Department department = (Department) q.setMaxResults(1).uniqueResult();
        session.close();
        return department;
    }
}
