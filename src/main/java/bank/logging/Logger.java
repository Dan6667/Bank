//package bank.logging;
//
//import bank.Bank;
//import bank.entities.bills.Bill;
//import bank.entities.clients.Client;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Aspect
//@Component
//public class Logger {
//
//    private List<Client> clients;
//    @Autowired
//    private Bank bank;
//
//    //Printing information about customers and their bills
//    @AfterReturning("execution(void bank.timeManagement.TimeManager.start())")
//    public void billsUpdated(){
//        clients = bank.getClients();
//        for(Client customer: clients){
//            System.out.println("Customers name: " + customer.getName());
//            System.out.println("Customer has " + customer.getBillsAmount() + " bills\n");
//
//            int i = 1;
//            for(Bill bill: customer.getBills()){
//                System.out.println("Bill " + i + ":");
//                System.out.println("Bill Type: " + bill.type);
//                System.out.println("Bill Name: " + bill.getName());
//                System.out.println("Money: " + bill.getMoney() + "\n");
//                ++i;
//            }
//        }
//    }
//}
