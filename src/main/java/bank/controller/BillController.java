package bank.controller;

import bank.Bank;
import bank.entity.bills.CreditBill;
import bank.entity.bills.DepositBill;
import bank.entity.bills.SimpleBill;
import bank.entity.clients.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/{clientId}")
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

    @RequestMapping(value = "/simple", method = RequestMethod.POST)
    public String createSimpleBill(@PathVariable("clientId") long clientId, SimpleBill bill){
        Client client = bank.getClient(clientId);
        client.addBill(bill);
        bill.setOwner(client);
        return "redirect: http://localhost:8080/bank_war_exploded/client";
    }

    @RequestMapping(value = "/credit", method = RequestMethod.POST)
    public String createCreditBill(@PathVariable("clientId") long clientId, CreditBill bill){
        Client client = bank.getClient(clientId);
        client.addBill(bill);
        bill.setOwner(client);
        return "client";
    }

    @RequestMapping(value = "/deposit", method = RequestMethod.POST)
    public String createDepositBill(@PathVariable("clientId") long clientId, DepositBill bill){
        Client client = bank.getClient(clientId);
        client.addBill(bill);
        bill.setOwner(client);
        return "client";
    }

    @RequestMapping(value = "/simple", method = RequestMethod.GET)
    public String simpleBill(){
        return "simple";
    }

    @RequestMapping(value = "/credit", method = RequestMethod.GET)
    public String creditBill(){
        return "credit";
    }

    @RequestMapping(value = "/deposit", method = RequestMethod.GET)
    public String depositBill(){
        return "deposit";
    }
}
