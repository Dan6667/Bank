package bank.entity.bills;

import bank.BillRates;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "bill")
@DiscriminatorValue("D")
public class DepositBill extends Bill {

    @Transient
    private double increasingRate = BillRates.DEPOSIT_RATE;
    @Transient
    private double depositLimit = BillRates.DEPOSIT_LIMIT;

    public DepositBill(String name){
        super(name);
    }

    public DepositBill(String name, double money){
        super(name, money);
    }

    public DepositBill(){
        type = Bills.DEPOSIT;
    }

    @Override
    public double takeMoney(double amount){
        if(amount - money > 0){
            money -= amount;
            bank.updateBill(this);
            return amount;
        }
        return 0;
    }

    @Override
    public void update(){
        if(money >= depositLimit){
            money *= increasingRate;
        }
    }
}
