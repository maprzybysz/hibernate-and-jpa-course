package pl.maprzybysz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.maprzybysz.entity.Customer;
import pl.maprzybysz.entity.CustomerDetails;
import pl.maprzybysz.entity.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class App40OrphantRemoval {

    private static Logger logger = LogManager.getLogger(App40OrphantRemoval.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");


    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Order order = em.find(Order.class, 1L);
        em.remove(order);

        em.getTransaction().commit();
        em.close();


    }
}

