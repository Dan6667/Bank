package bank.controller;

import bank.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@Controller
@RequestMapping("/clients")
public class ClientsController {
    private Bank bank;

    @Autowired
    public ClientsController(Bank bank){
        this.bank = bank;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getClients(Model model){
        model.addAttribute(bank.getClients());
        return "clients";
    }

    @RequestMapping(value = "/{clientId}", method = RequestMethod.GET)
    public String getClient(@PathVariable("clientId") long clientId, Model model){
        model.addAttribute(bank.getClient(clientId));
        model.addAttribute(new ArrayList<>(bank.getClient(clientId).getBills()));
        return "client";
    }

}