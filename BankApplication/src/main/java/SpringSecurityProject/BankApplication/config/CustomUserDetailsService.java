package SpringSecurityProject.BankApplication.config;


import SpringSecurityProject.BankApplication.model.Customers;
import SpringSecurityProject.BankApplication.repository.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomerRepo customerRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customers customers = customerRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User details not found for the user "+username));
        List<GrantedAuthority> grantedAuthorityList = List.of(new SimpleGrantedAuthority(customers.getRole()));
        return new User(customers.getEmail(), customers.getPassword(), grantedAuthorityList);
    }
}
