package bank.controller;

import bank.Bank;
import bank.entities.clients.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//import bank.dao.ClientDao;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private Bank bank;

    @Autowired
    public RegisterController(Bank bank){
        this.bank = bank;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String register(){
        return "registerForm";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String registerProcessing(Client client){
        bank.addClient(client);
        return "home";
    }
}