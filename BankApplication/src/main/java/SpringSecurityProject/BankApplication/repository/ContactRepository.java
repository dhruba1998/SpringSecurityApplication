package SpringSecurityProject.BankApplication.repository;

import SpringSecurityProject.BankApplication.model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<Contact,String> {

}
