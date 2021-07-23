package pl.maprzybysz.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.maprzybysz.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CustomerDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Customer getCustomer(Long id){
        Customer customer = em.find(Customer.class, id);
        return customer;
    }

    @Transactional
    public List<Customer> getCustomers(){
        return em.createQuery("select c from Customer c", Customer.class).getResultList();
    }
}
