package bank.service;

import bank.dao.GenericHibernateDao;
import bank.entities.bills.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService implements IBillService {
    private GenericHibernateDao<Bill> dao;

    @Autowired
    public void setDao(GenericHibernateDao<Bill> daoToSet) {
        dao = daoToSet;
        dao.setClazz(Bill.class);
    }

    public Bill findBill(final long id){
        return dao.findOne(id);
    }

//    public List<Bill> findClientBills(Client client){
//        return dao.findEntityWhere(client);
//    }

    public List<Bill> findAllBills(){
        return dao.findAll();
    }

    public void createBill(final Bill bill){
        dao.create(bill);
    }

    public void updateBill(final Bill bill){
        dao.update(bill);
    }

    public void deleteBill(final Bill bill){
        dao.delete(bill);
    }

    public void deleteBillById(final long billId){
        dao.deleteById(billId);
    }
}