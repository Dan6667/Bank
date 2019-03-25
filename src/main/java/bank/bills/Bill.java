package bank.bills;

public abstract class Bill {
    double money;
    String name;
    public Bills type;

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
}
