package bank.service;

import bank.dao.AbstractHibernateDao;
import bank.entities.clients.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService implements IClientService {
    private AbstractHibernateDao<Client> dao;

    @Autowired
    public void setDao(AbstractHibernateDao<Client> daoToSet) {
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
}