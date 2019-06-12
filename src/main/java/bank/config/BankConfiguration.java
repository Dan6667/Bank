package bank.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan(basePackages = {"bank"}, excludeFilters = {@ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = {"bank.controller.*", "bank.config.*"})})
@EnableAspectJAutoProxy
@EnableScheduling
public class BankConfiguration {
//
//    @Bean
//    public TaskScheduler taskScheduler() {
//        return new ConcurrentTaskScheduler();
//    }
}