package bank.config;

import bank.bills.CreditBill;
import bank.bills.DepositBill;
import bank.bills.SimpleBill;
import bank.customers.Customer;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

@Configuration
@ComponentScan(basePackages = "bank")
@EnableAspectJAutoProxy
@EnableScheduling
public class BankConfiguration {

    @Bean
    @Scope("prototype")
    public Customer customer(){
        return new Customer();
    }

    @Bean
    @Scope("prototype")
    public SimpleBill simpleBill(){
        return new SimpleBill();
    }

    @Bean
    @Scope("prototype")
    public DepositBill depositBill(){
        return new DepositBill();
    }

    @Bean
    @Scope("prototype")
    public CreditBill creditBill(){
        return new CreditBill();
    }

    @Bean
    public TaskScheduler taskScheduler() {
        return new ConcurrentTaskScheduler();
    }
}
