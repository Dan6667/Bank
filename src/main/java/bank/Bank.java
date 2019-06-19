package bank;

import bank.config.BankConfiguration;
import bank.entities.bills.*;
import bank.entities.clients.Client;
import bank.service.BillService;
import bank.service.ClientService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

//import bank.dao.BillDao;
//import bank.dao.ClientDao;

@Component
public class Bank {
    private static ApplicationContext context = new AnnotationConfigApplicationContext(BankConfiguration.class);


    /* In some other classes we will needed in customers or their bills.
    We provide this method to give them access to those fields through getters.
    In spring this class is singleton by default.
     */
    public static Bank getBank() {
//        if (context == null)
//            context = new AnnotationConfigApplicationContext(BankConfiguration.class);
        return context.getBean(Bank.class);
    }

    public void openBill(Client client, BillTypes type, String name, long money) {
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


    public static void main(String... args) {
        Bank bank = context.getBean(Bank.class);

        Client client1 = bank.newClient("John Jones");

        bank.openBill(client1, BillTypes.SIMPLE, "Just a bill", 10_000);
        bank.openBill(client1, BillTypes.CREDIT, "First credit bill", 0);
        CreditBill creditBill = (CreditBill) client1.getBill("First credit bill");
        creditBill.takeMoney(10_000);


        Client client2 = bank.newClient("Sam Smith");

        bank.openBill(client2, BillTypes.SIMPLE, "Just a bill again", 20_000);
        bank.openBill(client2, BillTypes.DEPOSIT, "My deposit bill", 1_000_000);

        ClientService cs = context.getBean(ClientService.class);
        List list = cs.findFieldsFromClientAndBill(client1, "id", "name");
        System.out.println("found");
    }

    public Client newClient(String name) {
        Client client = context.getBean(Client.class);
        client.setName(name);
        context.getBean(ClientService.class).createClient(client);
        return client;
    }

    public void addClient(Client client) {
        context.getBean(ClientService.class).createClient(client);
    }

    public List<Client> getClients() {
        return context.getBean(ClientService.class).findAllClients();
    }

    public Client getClient(long id) {
        return context.getBean(ClientService.class).findClient(id);
    }

    public List<Bill> getBills() {
        return context.getBean(BillService.class).findAllBills();
    }

    public void updateBills() {
        List<Bill> bills = getBills();

        if (bills != null && bills.size() > 0)
            for (Bill bill : bills) {
                bill.update();
                context.getBean(BillService.class).updateBill(bill);
            }
    }

    public void updateBill(Bill bill){
        context.getBean(BillService.class).updateBill(bill);
    }

    public void addBill(Bill bill){
        context.getBean(BillService.class).createBill(bill);
    }
}