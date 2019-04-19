package bank.controller;

import bank.Bank;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/clients")
public class ClientController {
    Bank bank = Bank.getBank();

    @RequestMapping(method = RequestMethod.GET)
    public String getClients(Model model){
        model.addAttribute(bank.getClients());
        return "clients";
    }

    @RequestMapping(value = "/{clientId}", method = RequestMethod.GET)
    public String getClient(@PathVariable("clientId") long clientId, Model model){
        model.addAttribute(bank.getClient(clientId));
        return "client";
    }

}
