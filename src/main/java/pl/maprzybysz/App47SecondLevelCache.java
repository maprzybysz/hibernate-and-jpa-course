package pl.maprzybysz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.jpa.QueryHints;
import pl.maprzybysz.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class App47SecondLevelCache {
    private static Logger logger = LogManager.getLogger(App47SecondLevelCache.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Customer customer = em.find(Customer.class, 1L);
        logger.info(customer);
        logger.info(customer.getOrders());
        em.getTransaction().commit();
        em.close();

        em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        customer = em.find(Customer.class, 1L);
        logger.info(customer);
        logger.info(customer.getOrders());
        em.getTransaction().commit();
        em.close();

    }
}

