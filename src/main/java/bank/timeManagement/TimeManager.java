//package bank.timeManagement;
//
//
//import bank.Bank;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//@Component
//public class TimeManager {
//    @Autowired
//    private Bank bank;
//    //We update bills every 10 seconds. Initial delay is 3 sec.
//    @Scheduled(fixedDelay = 10_000, initialDelay = 3_000)
//    public void start() {
//        bank.updateBills();
//    }
//}
