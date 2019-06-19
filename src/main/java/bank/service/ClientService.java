package bank.service;

import bank.dao.AbstractHibernateDao;
import bank.dao.GenericHibernateDao;
import bank.entities.clients.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService implements IClientService {
    private AbstractHibernateDao<Client> dao;

    @Autowired
    public void setDao(GenericHibernateDao<Client> daoToSet) {
        dao = daoToSet;
        dao.setClazz(Client.class);
    }

    public Client findClient(final long id){
        return dao.findOne(id);
    }

    public List<Client> findAllClients(){
        return dao.findAll();
    }

    public void createClient(final Client client){
        dao.create(client);
    }

    public void updateClient(final Client client){
        dao.update(client);
    }

    public void deleteClient(final Client client){
        dao.delete(client);
    }

    public void deleteClientById(final long clientId){
        dao.deleteById(clientId);
    }

    public List<Client> findFieldsFromClientAndBill(Client client, String clientField, String billField){
        return dao.findFieldsFromClientAndBill(client, clientField, billField);
    }
}