package bank.entities.clients;

import bank.entities.bills.Bill;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Entity
@Table(name = "client")
@Component
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "bills")
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Bill> bills = new ArrayList<>();

    public Client(){}

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public int getBillsAmount(){
        return bills.size();
    }

    public List<Bill> getBills(){
        return bills;
    }

    public void addBill(Bill bill){
        bills.add(bill);
        bill.update();
    }

    public Bill getBill(String name){
        for(Bill bill: bills){
            if(bill.getName().equals(name))
                return bill;
        }
        throw new NoSuchElementException();
    }

    public Bill getBill(long id){
        for(Bill bill: bills){
            if(bill.getId() == id)
                return bill;
        }
        throw new NoSuchElementException();
    }

    public boolean deleteBill(Bill bill){
        return bills.remove(bill);
    }

    public long getId(){
        return id;
    }
}