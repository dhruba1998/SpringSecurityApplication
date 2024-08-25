package SpringSecurityProject.BankApplication.controllers;


import SpringSecurityProject.BankApplication.config.CustomAuthenticationManager;
import SpringSecurityProject.BankApplication.model.Customers;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class loginController {

    private AuthenticationManager authenticationManager ;
    @PostMapping("/login")
    public String login(@RequestBody Customers customer){
        Authentication authentication = new UsernamePasswordAuthenticationToken(customer.getEmail(),customer.getPassword());
        Authentication authenticatedObject = authenticationManager.authenticate(authentication);
        if(authenticatedObject.isAuthenticated())
            return "Success";

        return "Fail";
    }
}
