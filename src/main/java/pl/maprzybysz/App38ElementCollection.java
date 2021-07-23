package pl.maprzybysz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.maprzybysz.entity.Address;
import pl.maprzybysz.entity.AddressType;
import pl.maprzybysz.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class App38ElementCollection {

    private static Logger logger = LogManager.getLogger(App38ElementCollection.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");


    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

       Customer customer = new Customer();
       customer.setFirstname("Customer 1");
       customer.setLastname("Customer 1");
       customer.setCreated(LocalDateTime.now());
       customer.setUpdated(LocalDateTime.now());
       customer.setAddress(Arrays.asList(
               new Address(AddressType.BILLING, "Polna 10/10", "00-00", "Warszawa"),
               new Address(AddressType.SHIPPING, "Polna 10/10", "00-00", "Warszawa"),
               new Address(AddressType.INVOICE, "Polna 10/10", "00-00", "Warszawa"))
       );
       em.persist(customer);
       em.flush();
       em.clear();
        Customer customer1 = em.find(Customer.class, customer.getId());
        logger.info(customer1);
        logger.info(customer1.getAddress());
        em.getTransaction().commit();
        em.close();


    }
}

