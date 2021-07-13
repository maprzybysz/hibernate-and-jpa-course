package pl.maprzybysz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.maprzybysz.entity.Order;
import pl.maprzybysz.entity.OrderRow;
import pl.maprzybysz.entity.Product;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App28NplusOne {

    private static Logger logger = LogManager.getLogger(App28NplusOne.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");


    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
/*
        List<Order> orders = em.createQuery("select o from Order o", Order.class).getResultList();
        logger.info("Ilość zamówień: "+orders.size());
        for (Order order : orders) {
            logger.info(order.getId());
            logger.info(order.getOrderRows());
        }
*/
        /*List<Order> orders =
                em.createQuery("select distinct o from Order o inner join fetch o.orderRows", Order.class).getResultList();
        logger.info("Ilość zamówień: "+orders.size());
        for (Order order : orders) {
            logger.info(order.getId());
            logger.info(order.getOrderRows());
        }
*/
 /*       EntityGraph entityGraph = em.getEntityGraph("order-and-rows");
        List<Order> orders = em.createQuery("select o from Order o", Order.class)
                .setHint("javax.persistence.fetchgraph", entityGraph)
                .getResultList();
        logger.info("Ilość zamówień: "+orders.size());
        for (Order order : orders) {
            logger.info(order.getId());
            logger.info(order.getOrderRows());
        }
  */
        List<Order> orders = em.createQuery("select o from Order o", Order.class).getResultList();
        logger.info("Ilość zamówień: "+orders.size());
        for (Order order : orders) {
            logger.info(order.getId());
            logger.info(order.getOrderRows());
        }
        em.getTransaction().commit();
        em.close();


    }
}

