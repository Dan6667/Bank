package bank.customers;

import bank.bills.Bill;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Customer {

    private String name;

    private ArrayList<Bill> bills = new ArrayList<>();

    public Customer(){}

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public int getBillsAmount(){
        return bills.size();
    }

    public ArrayList<Bill> getBills(){
        return bills;
    }

    public void addBill(Bill bill){
        bills.add(bill);
    }

    public Bill getBill(String name){
        for(Bill bill: bills){
            if(bill.getName().equals(name))
                return bill;
        }
        throw new NoSuchElementException();
    }

    public boolean deleteBill(Bill bill){
        return bills.remove(bill);
    }
}
