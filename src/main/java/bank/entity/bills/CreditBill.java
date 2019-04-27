package bank.entity.bills;

import bank.BillRates;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bill")
@DiscriminatorValue("C")
public class CreditBill extends Bill{

    private double creditRate = BillRates.CREDIT_RATE;
    private double creditLimit = BillRates.CREDIT_LIMIT;

    public CreditBill(String name){
        super(name);
    }

    public CreditBill(String name, double money){
        super(name, money);
    }

    public CreditBill(){
        type = Bills.CREDIT;
    }

    @Override
    public double takeMoney(double amount) {
        if(amount - money > creditLimit){
            money -= amount;
            bank.updateBill(this);
            return amount;
        }
        return 0;
    }

    @Override
    public void update() {
        if(money < 0){
            money *= creditRate;
        }
    }
}
