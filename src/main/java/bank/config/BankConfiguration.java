package bank.config;

import bank.dao.BillDao;
import bank.dao.ClientDao;
import bank.entity.bills.CreditBill;
import bank.entity.bills.DepositBill;
import bank.entity.bills.SimpleBill;
import bank.entity.clients.Client;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

@Configuration
@ComponentScan(basePackages = {"bank"}, excludeFilters = {@ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = {"bank.controller.*", "bank.config.*"})})
@EnableAspectJAutoProxy
@EnableScheduling
public class BankConfiguration {

    @Bean
    @Scope("prototype")
    public Client client(){
        return new Client();
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
    @Scope("prototype")
    public ClientDao clientDao(){
        return new ClientDao();
    }

    @Bean
    @Scope("prototype")
    public BillDao billDao(){
        return new BillDao();
    }

    @Bean
    public TaskScheduler taskScheduler() {
        return new ConcurrentTaskScheduler();
    }
}
