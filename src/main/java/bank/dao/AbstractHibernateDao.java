package bank.dao;

import bank.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractHibernateDao<T extends Serializable> {

    protected Class<T> clazz;

//    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public final void setClazz(Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    public T findOne(long id) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        T t = session.get(clazz, id);
        transaction.commit();
        session.close();
        return t;
    }

    public List<T> findAll() {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<T> ts = session.createQuery("from " + clazz.getName()).list();
        transaction.commit();
        session.close();
        return ts;
    }

    public void create(T entity) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
    }

    public void update(T entity) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
    }

    public void delete(T entity) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
}

    public void deleteById(long entityId) {
        T entity = findOne(entityId);
        delete(entity);
    }

    protected final Session getCurrentSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }
}