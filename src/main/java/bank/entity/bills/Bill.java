package bank.entity.bills;

import bank.dao.BillDao;
import bank.entity.clients.Client;

import javax.persistence.*;

@Entity
@Table(name = "bill")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name="discriminator",
        discriminatorType=DiscriminatorType.STRING
)
@DiscriminatorValue(value="B")
public abstract class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    private int id;

    @Column(name = "money")
    double money;

    @Column(name = "name")
    String name;

    @Column(name = "type")
    public Bills type;

    @ManyToOne
    @JoinColumn(name="client_id")
    public Client owner;

    Bill(String name){
        money = 0;
        this.name = name;
    }

    Bill(String name, double money){
        this.money = money;
        this.name = name;
    }

    Bill(){}

    public void update(){
        BillDao billDao = new BillDao();
        billDao.updateBill(this);
    }

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
}
