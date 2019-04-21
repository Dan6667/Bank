package bank;

import bank.config.BankConfiguration;
//import bank.dao.BillDao;
//import bank.dao.ClientDao;
import bank.entity.bills.*;
import bank.entity.clients.Client;
import bank.service.BillService;
import bank.service.ClientService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Bank {
    private List<Client> clients = new ArrayList<>();
    private static ApplicationContext context;


    /* In some other classes we will needed in customers or their bills.
    We provide this method to give them access to those fields through getters.
    In spring this class is singleton by default.
     */
    public static Bank getBank(){
        if(context == null)
            context = new AnnotationConfigApplicationContext(BankConfiguration.class);
        return context.getBean(Bank.class);
    }

    public void openBill(Client client, Bills type, String name, long money) {
        BillService billService = context.getBean(BillService.class);
        Bill bill;
        switch (type) {
            case SIMPLE:
                bill = context.getBean(SimpleBill.class);
                break;
            case DEPOSIT:
                bill = context.getBean(DepositBill.class);
                break;
            default:
                bill = context.getBean(CreditBill.class);
                break;
        }

        bill.setMoney(money);
        bill.setName(name);
        bill.setOwner(client);
        client.addBill(bill);

        billService.createBill(bill);
    }

    /*
    Creating beans in main method is not really good, but here it is used to
    check how application works entirely. Later this application will be expanded
    to web application, so bean creation will be removed from main method.
     */
    public static void main(String... args) {
        context = new AnnotationConfigApplicationContext(BankConfiguration.class);
        Bank bank = context.getBean(Bank.class);

        Client client1 = bank.newClient("John Jones");

        bank.openBill(client1, Bills.SIMPLE, "Just a bill", 10_000);
        bank.openBill(client1, Bills.CREDIT, "First credit bill", 0);
        CreditBill creditBill = (CreditBill) client1.getBill("First credit bill");
        creditBill.takeMoney(10_000);


        Client client2 = bank.newClient("Sam Smith");

        bank.openBill(client2, Bills.SIMPLE, "Just a bill again", 20_000);
        bank.openBill(client2, Bills.DEPOSIT, "My deposit bill", 1_000_000);

    }

    public Client newClient(String name){
        Client client = context.getBean(Client.class);
        client.setName(name);
        clients.add(client);
        context.getBean(ClientService.class).createClient(client);
        return client;
    }

    public void addClient(Client client){
        clients.add(client);
        context.getBean(ClientService.class).createClient(client);
    }

    public List<Client> getClients() {
        if(clients.size() == 0){
            clients = context.getBean(ClientService.class).findAllClients();
        }
        return clients;
    }

    public Client getClient(long id){
        for(Client client: clients){
            if(client.getId() == id)
                return client;
        }
        return null;
    }

    public ArrayList<Bill> getBills(){
        ArrayList<Bill> bills = new ArrayList<>();

        if(clients.size() == 0){
            clients = getClients();
        }

        for(Client customer: clients){
            bills.addAll(customer.getBills());
        }
        return bills;
    }

    public void updateBills(){
        List<Bill> bills = getBills();

        for(Bill bill: bills){
            bill.update();
            context.getBean(BillService.class).updateBill(bill);
        }
    }
}
