package bank.dao;

import bank.entities.BankEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class GenericHibernateDao<T extends BankEntity> extends AbstractHibernateDao<T> implements IGenericDao<T> {

//    public <Entity> List<T> findEntityWhere(Entity entity){
//        Session session = getCurrentSession();
//        Transaction transaction = session.beginTransaction();
//        Query query = session.createQuery("FROM " + clazz.getName() + " AS bill WHERE bill.owner = " + entity);
//
//        transaction.commit();
//        session.close();
//
//        return query.getResultList();
//    }

    public List<T> findFieldsFromClientAndBill(T client, String clientField, String billField){
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();

        String selectQuery = "SELECT c." + clientField +
                ", b." + billField +
                " FROM Client c JOIN c.bills b WHERE c.id = :id";

        Query query = session.createQuery(selectQuery)
                .setParameter("id", client.getId());

        List result = query.getResultList();

        transaction.commit();
        session.close();
        return result;
    }

}
