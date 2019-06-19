//package bank.service;
//
//import bank.dao.GenericHibernateDao;
//import bank.entities.BankEntity;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class GenericService<T extends BankEntity> {
//    private GenericHibernateDao<T> dao;
//
//    @Autowired
//    public void setDao(GenericHibernateDao<T> daoToSet) {
//        dao = daoToSet;
//        dao.setClazz(T);
//    }
//
//    public List<T> findFieldsFromClientAndBill(T client, String clientField, String billField){
//        return dao.findFieldsFromClientAndBill(client, clientField, billField);
//    }
//}
