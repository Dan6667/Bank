package bank.entities.bills;

//import bank.dao.BillDao;

import bank.Bank;
import bank.entities.BankEntity;
import bank.entities.clients.Client;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bill")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name="discriminator",
        discriminatorType=DiscriminatorType.STRING
)
@DiscriminatorValue(value="B")
public abstract class Bill extends BankEntity implements Serializable {

    @Transient
    @Autowired
    Bank bank;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "money")
    double money;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    public BillTypes type;

    @ManyToOne
    @JoinColumn(name="client_id")
    private Client owner;

    Bill(String name){
        money = 0;
        this.name = name;
    }

    Bill(String name, double money){
        this.money = money;
        this.name = name;
    }

    Bill(){}

    public abstract void update();

    public abstract double takeMoney(double amount);

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    public long getId() {
        return id;
    }
}