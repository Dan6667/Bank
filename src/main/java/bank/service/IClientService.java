package bank.service;

import bank.entity.clients.Client;

import java.util.List;

public interface IClientService {
    Client findClient(final long id);

    List<Client> findAllClients();

    void createClient(final Client client);

    void updateClient(final Client client);

    void deleteClient(final Client client);

    void deleteClientById(final long clientId);
}
