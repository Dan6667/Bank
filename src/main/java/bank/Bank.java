package bank;

import bank.bills.*;
import bank.config.BankConfiguration;
import bank.customers.Customer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Bank {
    private ArrayList<Customer> customers = new ArrayList<>();
    private static ApplicationContext context;


    /* In some other classes we will needed in customers or their bills.
    We provide this method to give them access to those fields through getters.
    In spring this class is singleton by default.
     */
    public static Bank getBank(){
        return context.getBean(Bank.class);
    }

    private void openBill(Customer customer, Bills type, String name, long money) {
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
        customer.addBill(bill);
    }

    /*
    Creating beans in main method is not really good, but here it is used to
    check how application works entirely. Later this application will be expanded
    to web application, so bean creation will be removed from main method.
     */
    public static void main(String... args) {
        context = new AnnotationConfigApplicationContext(BankConfiguration.class);

        Bank bank = context.getBean(Bank.class);

        Customer customer1 = context.getBean(Customer.class);
        customer1.setName("John Jones");
        bank.customers.add(customer1);

        bank.openBill(customer1, Bills.SIMPLE, "Just a bill", 10_000);
        bank.openBill(customer1, Bills.CREDIT, "First credit bill", 0);
        CreditBill creditBill = (CreditBill) customer1.getBill("First credit bill");
        creditBill.takeMoney(10_000);

        Customer customer2 = context.getBean(Customer.class);
        customer2.setName("Sam Smith");
        bank.customers.add(customer2);

        bank.openBill(customer2, Bills.SIMPLE, "Just a bill again", 20_000);
        bank.openBill(customer2, Bills.DEPOSIT, "My deposit bill", 1_000_000);

    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public ArrayList<Bill> getBills(){
        ArrayList<Bill> bills = new ArrayList<>();

        for(Customer customer: customers){
            bills.addAll(customer.getBills());
        }
        return bills;
    }
}
