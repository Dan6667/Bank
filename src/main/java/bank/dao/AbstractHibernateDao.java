package bank.dao;

import bank.config.HibernateUtil;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractHibernateDao<T extends Serializable> {

    private Class<T> clazz;

//    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public final void setClazz(Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    public T findOne(long id) {
        Session session = getCurrentSession();
        T t = session.get(clazz, id);
        session.close();
        return t;
    }

    public List<T> findAll() {
        Session session = getCurrentSession();
        List<T> ts = session.createQuery("from " + clazz.getName()).list();
        session.close();
        return ts;
    }

    public void create(T entity) {
        Session session = getCurrentSession();
        session.persist(entity);
        session.close();
    }

    public void update(T entity) {
        Session session = getCurrentSession();
        session.merge(entity);
        session.close();
    }

    public void delete(T entity) {
        Session session = getCurrentSession();
        session.delete(entity);
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