package bank.config;


import bank.entity.bills.Bill;
import bank.entity.bills.CreditBill;
import bank.entity.bills.DepositBill;
import bank.entity.bills.SimpleBill;
import bank.entity.clients.Client;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "org.h2.Driver");
                settings.put(Environment.URL, "jdbc:h2:mem:bank");//jdbc:h2:mem:test
                settings.put(Environment.USER, "sa");
                settings.put(Environment.PASS, "    ");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.H2Dialect");
                settings.put(Environment.SHOW_SQL, "false");
                settings.put(Environment.HBM2DDL_AUTO, "create");
                settings.put(Environment.ENABLE_LAZY_LOAD_NO_TRANS, true);
//                settings.put(Environment.CONNECTION_PROVIDER, org.hibernate.connection.C3P0ConnectionProvider.class);
//                settings.put(Environment.C3P0_ACQUIRE_INCREMENT, 1);
//                settings.put(Environment.C3P0_IDLE_TEST_PERIOD, 60);


                configuration.setProperties(settings);
                configuration.addAnnotatedClass(Bill.class);
                configuration.addAnnotatedClass(SimpleBill.class);
                configuration.addAnnotatedClass(CreditBill.class);
                configuration.addAnnotatedClass(DepositBill.class);
                configuration.addAnnotatedClass(Client.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}