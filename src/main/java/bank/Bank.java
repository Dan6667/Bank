package bank;

import bank.config.BankConfiguration;
import bank.dao.BillDao;
import bank.dao.ClientDao;
import bank.entity.bills.*;
import bank.entity.clients.Client;
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
        return context.getBean(Bank.class);
    }

    private void openBill(Client client, Bills type, String name, long money) {
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
    }

    /*
    Creating beans in main method is not really good, but here it is used to
    check how application works entirely. Later this application will be expanded
    to web application, so bean creation will be removed from main method.
     */
    public static void main(String... args) {
        context = new AnnotationConfigApplicationContext(BankConfiguration.class);
        ClientDao clientDao = new ClientDao();
        BillDao billDao = new BillDao();

        Bank bank = context.getBean(Bank.class);

        Client client1 = context.getBean(Client.class);
        client1.setName("John Jones");
        clientDao.saveClient(client1);
        bank.clients.add(client1);

        bank.openBill(client1, Bills.SIMPLE, "Just a bill", 10_000);
        bank.openBill(client1, Bills.CREDIT, "First credit bill", 0);
        CreditBill creditBill = (CreditBill) client1.getBill("First credit bill");
        creditBill.takeMoney(10_000);
        billDao.saveBill(creditBill);
        billDao.saveBill(client1.getBill("Just a bill"));


        Client client2 = context.getBean(Client.class);
        client2.setName("Sam Smith");
        clientDao.saveClient(client2);
        bank.clients.add(client2);

        bank.openBill(client2, Bills.SIMPLE, "Just a bill again", 20_000);
        bank.openBill(client2, Bills.DEPOSIT, "My deposit bill", 1_000_000);

        billDao.saveBill(client2.getBill("Just a bill again"));
        billDao.saveBill(client2.getBill("My deposit bill"));
    }

    public List<Client> getClients() {
        if(clients.size() == 0){
            ClientDao clientDao = new ClientDao();
            clients =  clientDao.getClients();
        }
        return clients;
    }

    public ArrayList<Bill> getBills(){
        ArrayList<Bill> bills = new ArrayList<>();

        if(clients.size() == 0){
            ClientDao clientDao = new ClientDao();
            clients =  clientDao.getClients();
        }

        for(Client customer: clients){
            bills.addAll(customer.getBills());
        }
        return bills;
    }
}
