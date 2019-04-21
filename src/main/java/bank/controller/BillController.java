package bank.controller;

import bank.Bank;
import bank.entity.bills.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/{clientID}")
public class BillController {
    private Bank bank;

    @Autowired
    public BillController(Bank bank){
        this.bank = bank;
    }

    @RequestMapping(value = "/bills")
    public String getBills(@PathVariable("clientId") long clientId, Model model){
        model.addAttribute(bank.getClient(clientId).getBills());
        return "bills";
    }

    @RequestMapping(value = "/{billId}")
    public String getBill(@PathVariable("clientId") long clientId, @PathVariable("billId") long billId, Model model){
        model.addAttribute(bank.getClient(clientId).getBill(billId));
        return "bill";
    }

    @RequestMapping(value = "/createBill", method = RequestMethod.POST)
    public String createBill(@PathVariable("clientId") long clientId, Bill bill){
        bank.getClient(clientId).addBill(bill);
        return "client";
    }
}
