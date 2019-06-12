package bank.entities.bills;

import org.springframework.stereotype.Component;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bill")
@DiscriminatorValue("S")
@Component
public class SimpleBill extends Bill{


    public SimpleBill(String name){
        super(name);
    }

    public SimpleBill(String name, double money){
        super(name, money);
    }

    public SimpleBill(){
        type = BillTypes.SIMPLE;
    }

    @Override
    public double takeMoney(double amount){
        if(money - amount > 0){
            money -= amount;
            bank.updateBill(this);
            return amount;
        }
        return 0;
    }

    @Override
    public void update(){
    }

}