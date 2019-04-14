package bank.dao;

import bank.config.HibernateUtil;
import bank.entity.clients.Client;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ClientDao {
    public void saveClient(Client client) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(client);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Client> getClients() {
        Transaction tx;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            List<Client> clients = session.createQuery("from Client", Client.class).list();
            tx.commit();
            return clients;
        }
    }

    public void updateCleint(Client client) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(client);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteCleint(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Client client = session.load(Client.class, id);
            session.delete(client);
            transaction.commit();
            session.flush();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
