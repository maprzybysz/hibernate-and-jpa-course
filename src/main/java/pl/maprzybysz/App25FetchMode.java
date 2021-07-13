package pl.maprzybysz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.maprzybysz.entity.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class App25FetchMode {

    private static Logger logger = LogManager.getLogger(App25FetchMode.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");


    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        //Order order = em.find(Order.class, 1L);

        TypedQuery<Order> order = em.createQuery("select o from Order o", Order.class);



        em.getTransaction().commit();
        em.close();


    }
}
