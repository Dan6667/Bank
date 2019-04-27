package bank.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class GenericHibernateDao<T extends Serializable> extends AbstractHibernateDao<T> implements IGenericDao<T> {

    public <Entity> List<T> findEntityWhere(Entity entity){
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM " + clazz.getName() + " AS bill WHERE bill.owner = " + entity);
        return query.list();
    }
}
