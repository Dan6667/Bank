package bank.dao;

import bank.config.HibernateUtil;
import bank.entity.bills.Bill;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BillDao {
    public void saveBill(Bill bill) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(bill);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Bill> getBills() {
        Transaction tx;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            List<Bill> bills = session.createQuery("from Bill", Bill.class).list();
            tx.commit();
            return bills;
        }
    }

    public void updateBill(Bill bill) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(bill);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
