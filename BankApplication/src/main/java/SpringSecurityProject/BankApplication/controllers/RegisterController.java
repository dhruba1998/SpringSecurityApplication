package SpringSecurityProject.BankApplication.controllers;

import SpringSecurityProject.BankApplication.model.Customers;
import SpringSecurityProject.BankApplication.repository.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegisterController {

    private final CustomerRepo customerRepo;
    private final PasswordEncoder passwordEncoder;
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customers customer){
        try{
            //Checking if password is leaked already
            HaveIBeenPwnedRestApiPasswordChecker passwordChecker = new HaveIBeenPwnedRestApiPasswordChecker();
            boolean isPasswordLeaked =  passwordChecker.check(customer.getPassword()).isCompromised();

            if(isPasswordLeaked){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please try with strong password");
            }

            String encodedPassword = passwordEncoder.encode(customer.getPassword());
            customer.setPassword(encodedPassword);
            Customers savedCustomer = customerRepo.save(customer);

            if(savedCustomer.getId()>0){
                return ResponseEntity.status(HttpStatus.CREATED).body("User details are saved successfully");
            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to save user details");
            }
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User details failed to save due to " + e.getMessage());
        }
    }
}
