package SpringSecurityProject.BankApplication.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticeController {


    @GetMapping("/notice")
    public String showNotice(){
        return "Here are all the notices";
    }
}
