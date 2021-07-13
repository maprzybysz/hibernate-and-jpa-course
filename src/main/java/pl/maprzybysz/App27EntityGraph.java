package pl.maprzybysz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.maprzybysz.entity.Order;
import pl.maprzybysz.entity.OrderRow;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App27EntityGraph {

    private static Logger logger = LogManager.getLogger(App27EntityGraph.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");


    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        /*EntityGraph entityGraph = em.getEntityGraph("order-rows");
        Map<String, Object> map = new HashMap<>();
        map.put("javax.persistence.fetchgraph", entityGraph);
        Order order = em.find(Order.class, 1L, map);
        logger.info(order);
        for (OrderRow orderRow : order.getOrderRows()) {
            logger.info(orderRow);
            logger.info(orderRow.getProduct());
        }*/

        EntityGraph<Order> entityGraph = em.createEntityGraph(Order.class);
        entityGraph.addAttributeNodes("orderRows");
        entityGraph.addAttributeNodes("customer");
        Subgraph<OrderRow> orderRows = entityGraph.addSubgraph("orderRows");
        orderRows.addAttributeNodes("product");
        Map<String, Object> map = new HashMap<>();
        map.put("javax.persistence.fetchgraph", entityGraph);
        //Order order = em.find(Order.class, 1L, map);
        List<Order> orders = em.createQuery("select o from Order o", Order.class)
                .setHint("javax.persistence.fetchgrapgh", entityGraph).getResultList();
        for (Order order : orders) {
            logger.info(order);
            for (OrderRow orderRow : order.getOrderRows()) {
                logger.info(orderRow);
                logger.info(orderRow.getProduct());
        }

        em.getTransaction().commit();
        em.close();


    }
}
}
