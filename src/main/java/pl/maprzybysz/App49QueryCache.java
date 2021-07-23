package pl.maprzybysz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.jpa.QueryHints;
import pl.maprzybysz.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class App49QueryCache {
    private static Logger logger = LogManager.getLogger(App49QueryCache.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Customer> customer = em.createQuery("select c from Customer c", Customer.class)
                .setHint(QueryHints.HINT_CACHEABLE, true)
                .getResultList();
        logger.info(customer);
        em.getTransaction().commit();
        em.close();

        em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        customer = em.createQuery("select c from Customer c", Customer.class)
                .setHint(QueryHints.HINT_CACHEABLE, true)
                .getResultList();
        logger.info(customer);
        em.getTransaction().commit();
        em.close();
    }
}

