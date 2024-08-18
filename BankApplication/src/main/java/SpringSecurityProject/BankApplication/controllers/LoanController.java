package SpringSecurityProject.BankApplication.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanController {


    @GetMapping("/myLoans")
    public String showLoansDetails(){
        return "Here are the loan details";
    }
}
