package com.foxminded.university.dao.hibernate;

import com.foxminded.university.dao.ConnectionFactory;
import com.foxminded.university.dao.GroupDao;
import com.foxminded.university.domain.Group;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class GroupDaoImpl implements GroupDao {
    private static SessionFactory sessionFactory = ConnectionFactory.getSessionFactory();

    public void create(Group group) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(group);
        transaction.commit();
        session.close();
    }

    public Group findOne(Long id) {
        return sessionFactory.openSession().get(Group.class, id);
    }

    public Group update(Group group) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(group);
        session.getTransaction().commit();
        session.close();
        return group;
    }

    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Group group = session.get(Group.class, id);
        session.delete(group);
        session.getTransaction().commit();
        session.close();
    }

    public List<Group> findAll() {
        Session session = sessionFactory.openSession();
        List<Group> groups = session.createQuery("FROM Group ").list();
        session.close();
        return groups;
    }

    public Group findByStudent(long studentId) {
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("Select g from Group g, Student s where s.group.id = g.id " +
                "and s.id = :studentId");
        q.setParameter("studentId", studentId);
        Group group = (Group) q.setMaxResults(1).uniqueResult();
        session.close();
        return group;
    }
}
