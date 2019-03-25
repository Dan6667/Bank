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

    private static ArrayList<Customer> customers = new ArrayList<>();
    private static ApplicationContext context;

    public void openBill(Customer customer, Bills type, String name, long money) {
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

    public static void main(String... args) {
        context = new AnnotationConfigApplicationContext(BankConfiguration.class);

        Bank bank = context.getBean(Bank.class);
        //TimeManager timeManager = context.getBean(TimeManager.class);

        Customer customer1 = context.getBean(Customer.class);
        customer1.setName("John Jones");
        customers.add(customer1);

        bank.openBill(customer1, Bills.SIMPLE, "Just a bill", 10_000);
        bank.openBill(customer1, Bills.CREDIT, "First credit bill", 0);
        CreditBill creditBill = (CreditBill) customer1.getBill("First credit bill");
        creditBill.takeMoney(10_000);

        Customer customer2 = context.getBean(Customer.class);
        customer2.setName("Sam Smith");
        customers.add(customer2);

        bank.openBill(customer2, Bills.SIMPLE, "Just a bill again", 20_000);
        bank.openBill(customer2, Bills.DEPOSIT, "My deposit bill", 1_000_000);

    }

//    @Lookup
//    public Customer getCustomer(){
//        return null;
//    }
//
//    @Lookup
//    public SimpleBill getSimpleBill(){
//        return null;
//    }
//    @Lookup
//    public CreditBill getCreditBill(){
//        return null;
//    }
//    @Lookup
//    public DepositBill getDepositBill(){
//        return null;
//    }

    public static ArrayList<Customer> getCustomers() {
        return customers;
    }

    public static ArrayList<Bill> getBills(){
        ArrayList<Bill> bills = new ArrayList<>();

        for(Customer customer: customers){
            for(Bill bill: customer.getBills())
                bills.add(bill);
        }
        return bills;
    }
}
