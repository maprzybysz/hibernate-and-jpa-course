package pl.maprzybysz.service;

import org.springframework.stereotype.Service;
import pl.maprzybysz.dao.CustomerDao;
import pl.maprzybysz.entity.Customer;
import pl.maprzybysz.repository.CustomerRepository;


import java.util.List;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).orElseThrow();
    }

    public List<Customer> getCustomers() {
    return customerRepository.findAll();
    }
}
