package SpringSecurityProject.BankApplication.controllers;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @GetMapping("/test")
    public String showLoginPage(HttpServletRequest request){

        return "Testing Successful " + request.getSession().getId();
    }


}

