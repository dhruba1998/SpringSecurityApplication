package SpringSecurityProject.BankApplication.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {

    @GetMapping("/myBalance")
    public String showBalanceDetails(){
        return "here is your bank balance details";
    }
}
