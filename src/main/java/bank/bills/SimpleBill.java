package bank.bills;

public class SimpleBill extends Bill{

    public SimpleBill(String name){
        super(name);
    }

    public SimpleBill(String name, double money){
        super(name, money);
    }

    public SimpleBill(){
        type = Bills.SIMPLE;
    }

    @Override
    public double takeMoney(double amount){
        if(money - amount > 0){
            money -= amount;
            return amount;
        }
        return 0;
    }

    @Override
    public void update(){
    }

}
