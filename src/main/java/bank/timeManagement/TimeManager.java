package bank.timeManagement;


import bank.Bank;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TimeManager {
    //We update bills every 10 seconds. Initial delay is 3 sec.
    @Scheduled(fixedDelay = 10_000, initialDelay = 3_000)
    public void start() {
        Bank.getBank().updateBills();
    }
}
