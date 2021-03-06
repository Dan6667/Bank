package bank.timeManagement;


import bank.Bank;
import bank.entity.bills.Bill;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TimeManager {
    private List<Bill> bills;
    //We update bills every 10 seconds. Initial delay is 3 sec.
    @Scheduled(fixedDelay = 10_000, initialDelay = 3_000)
    public void start() {
        updateBills();
        for (Bill bill : bills)
            bill.update();
    }

    private void updateBills(){
        bills = Bank.getBank().getBills();
    }
}
