package SpringSecurityProject.BankApplication.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @GetMapping("/myAccount")
    public String showAccountDetails(){
        return "Here is the account details";
    }
}
