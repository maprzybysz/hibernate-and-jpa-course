package pl.maprzybysz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.maprzybysz.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App48CollectionCache {
    private static Logger logger = LogManager.getLogger(App48CollectionCache.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        Customer customer = em.find(Customer.class, 1L);
        logger.info(customer);
        em.getTransaction().commit();
        em.close();

        em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        customer = em.find(Customer.class, 1L);
        logger.info(customer);
        em.getTransaction().commit();
        em.close();

    }
}

