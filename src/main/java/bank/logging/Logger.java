package bank.logging;

import bank.Bank;
import bank.bills.Bill;
import bank.customers.Customer;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Aspect
@Component
public class Logger {

    private ArrayList<Customer> customers;

    //Printing information about customers and their bills
    @AfterReturning("execution(void bank.timeManagement.TimeManager.start())")
    public void billsUpdated(){
        customers = Bank.getBank().getCustomers();
        for(Customer customer: customers){
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
