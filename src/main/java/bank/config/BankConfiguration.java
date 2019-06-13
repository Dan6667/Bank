package bank.config;

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
    public TaskScheduler taskScheduler() {
        return new ConcurrentTaskScheduler();
    }
}