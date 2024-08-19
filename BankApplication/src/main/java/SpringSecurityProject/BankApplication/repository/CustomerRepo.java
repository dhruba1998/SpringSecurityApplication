package SpringSecurityProject.BankApplication.repository;


import SpringSecurityProject.BankApplication.model.Customers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends CrudRepository<Customers,Integer> {
    Optional<Customers> findByEmail(String email);
}
