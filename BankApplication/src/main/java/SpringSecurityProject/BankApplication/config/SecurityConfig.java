package SpringSecurityProject.BankApplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests((request) -> request.anyRequest().authenticated());
        http.csrf(csrf -> csrf.disable());          // This is used to perform create,update,delete operation on db as it's disabled by default
        http.authorizeHttpRequests((request) -> {
            request.requestMatchers("/myAccount","/myBalance","/myCards","/myLoans").authenticated()
                    .requestMatchers("/notice","/contacts","/register").permitAll();
        });
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }


//    @Bean
//    public UserDetailsService userDetailsManager(){
//
//        UserDetails dhruba = User.withUsername("dhruba").password("{bcrypt}$2a$12$CFgjw0M9EDijMxJRWWki.uCU1NmeEurf.A7ZX7PScE7ukEdDjCkOq").authorities("admin").build();
//        UserDetails souvik = User.withUsername("souvik").password("{noop}souvikDas@123").authorities("user").build();
//
//        return new InMemoryUserDetailsManager(dhruba,souvik);
//    }

    /*
    The following bean is used to leverage the Users, Authorities tables provided by Spring Security Team
    DataSource is the mysql connection which is used as backend db, as we pass this parameter to the
    jdbcUserDetailsManager, it knows what is the db url and name to connect.
    If you want to use your own custom table then you need to create your own custom UserDetailsService/Manager
    and to communicate with your db you need to use spring-data-jpa(Repo layer).
     */

//    @Bean
//    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource){
//        return new JdbcUserDetailsManager(dataSource);
//    }


    @Bean
    public PasswordEncoder passwordEncoder(){
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker(){
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }





}
