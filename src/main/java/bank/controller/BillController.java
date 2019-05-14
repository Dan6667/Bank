package bank.controller;

import bank.Bank;
import bank.entity.bills.CreditBill;
import bank.entity.bills.DepositBill;
import bank.entity.bills.SimpleBill;
import bank.entity.clients.Client;
import bank.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/{clientId}")
public class BillController {
    private Bank bank;

    @Autowired
    private BillService billService;


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
    public String createSimpleBill(@PathVariable("clientId") long clientId, SimpleBill bill, ModelMap model){
        Client client = bank.getClient(clientId);
        bill.setOwner(client);
        client.addBill(bill);
        billService.createBill(bill);
        model.addAttribute(client);
        return "redirect:/{clientId}";
    }

    @RequestMapping(value = "/credit", method = RequestMethod.POST)
    public String createCreditBill(@PathVariable("clientId") long clientId, CreditBill bill, Model model){
        Client client = bank.getClient(clientId);
        bill.setOwner(client);
        client.addBill(bill);
        billService.createBill(bill);
        model.addAttribute(client);
        return "redirect:/{clientId}";
    }

    @RequestMapping(value = "/deposit", method = RequestMethod.POST)
    public String createDepositBill(@PathVariable("clientId") long clientId, DepositBill bill, Model model){
        Client client = bank.getClient(clientId);
        bill.setOwner(client);
        client.addBill(bill);
        billService.createBill(bill);
        model.addAttribute(client);
        return "redirect:/{clientId}";
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
