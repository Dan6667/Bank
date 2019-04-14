package bank.logging;

import bank.Bank;
import bank.entity.bills.Bill;
import bank.entity.clients.Client;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class Logger {

    private List<Client> clients;

    //Printing information about customers and their bills
    @AfterReturning("execution(void bank.timeManagement.TimeManager.start())")
    public void billsUpdated(){
        clients = Bank.getBank().getClients();
        for(Client customer: clients){
            System.out.println("Customers name: " + customer.getName());
            System.out.println("Customer has " + customer.getBillsAmount() + " bills\n");

            int i = 1;
            for(Bill bill: customer.getBills()){
                System.out.println("Bill " + i + ":");
                System.out.println("Bill Type: " + bill.type);
                System.out.println("Bill Name: " + bill.getName());
                System.out.println("Money: " + bill.getMoney() + "\n");
                ++i;
            }
        }
    }
}
