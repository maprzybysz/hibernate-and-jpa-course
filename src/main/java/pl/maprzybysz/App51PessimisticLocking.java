package pl.maprzybysz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.maprzybysz.entity.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class App51PessimisticLocking {
    private static Logger logger = LogManager.getLogger(App51PessimisticLocking.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

//        Order order = em.find(Order.class, 3L, LockModeType.PESSIMISTIC_WRITE);
//        logger.info(order);

        Order order = em.createQuery("select o from Order o where o.id=:id", Order.class)
                .setParameter("id", 3L)
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .getSingleResult();

        logger.info(order);

        em.getTransaction().commit();
        em.close();

    }
}

