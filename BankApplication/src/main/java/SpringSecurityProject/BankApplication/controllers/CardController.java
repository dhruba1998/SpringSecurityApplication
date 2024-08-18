package SpringSecurityProject.BankApplication.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {

    @GetMapping("/myCards")
    public String showCardsDetails(){
        return "Here is your card details";
    }
}
