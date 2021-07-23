package pl.maprzybysz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.jpa.QueryHints;
import pl.maprzybysz.entity.Customer;
import pl.maprzybysz.entity.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class App50OptimisticLocking {
    private static Logger logger = LogManager.getLogger(App50OptimisticLocking.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        EntityManager em2 = entityManagerFactory.createEntityManager();
        em2.getTransaction().begin();

        Order order = em.find(Order.class, 3L);
        Order order2 = em2.find(Order.class, 3L);
        order.setTotal(new BigDecimal("22.31"));

        logger.info(order);
        em.getTransaction().commit();
        em.close();

        order2.setTotal(new BigDecimal("22.41"));
        logger.info(order2);
        em2.getTransaction().commit();
        em2.close();

    }
}

