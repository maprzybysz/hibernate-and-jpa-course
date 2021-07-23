package pl.maprzybysz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.jpa.QueryHints;
import pl.maprzybysz.entity.Customer;
import pl.maprzybysz.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.QueryHint;
import java.time.LocalDateTime;

public class App46ReadOnly {
    private static Logger logger = LogManager.getLogger(App46ReadOnly.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();

            Customer customer = em.createQuery("select c from Customer c where c.id=:id", Customer.class)
                    .setHint(QueryHints.HINT_READONLY, true)
                    .setParameter("id", 1L)
                    .getSingleResult();
            em.getTransaction().begin();
            customer.setUpdated(LocalDateTime.now());
            em.merge(customer);
            logger.info(customer);
            em.getTransaction().commit();
            em.close();
    }
}

