package pl.maprzybysz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.maprzybysz.entity.Address;
import pl.maprzybysz.entity.AddressType;
import pl.maprzybysz.entity.Customer;
import pl.maprzybysz.entity.CustomerDetails;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

public class App39IdsMapping {

    private static Logger logger = LogManager.getLogger(App39IdsMapping.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");


    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Customer customer = em.find(Customer.class, 1L);
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setBirthPlace("Warszawa");
        customerDetails.setBirthDay(LocalDate.of(2000, 10, 22));
        customerDetails.setFatherName("Jan");
        customerDetails.setMotherName("Janka");
        customerDetails.setCustomer(customer);
        customer.setCustomerDetails(customerDetails);
        em.persist(customer);

        logger.info(customer);

        em.getTransaction().commit();
        em.close();


    }
}

