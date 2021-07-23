package pl.maprzybysz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.maprzybysz.entity.Customer;
import pl.maprzybysz.entity.Order;
import pl.maprzybysz.entity.RealProduct;
import pl.maprzybysz.entity.VirtualProduct;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class App42ComparingEntities {
    private static Logger logger = LogManager.getLogger(App42ComparingEntities.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Customer customer = em.find(Customer.class, 1L);
        logger.info(customer.getOrders());

//        em.clear();
//        Order order = em.find(Order.class, 4L);
//        logger.info(order.equals(getOrder(customer, 4L)));

//        Order order = new Order();
//        order.setTotal(new BigDecimal("19.99"));
//        order.setCreated(LocalDateTime.now());
//        order.setCustomer(customer);
//        em.persist(order);

        em.getTransaction().commit();
        em.close();
    }

    private static Order getOrder(Customer customer, long id) {
        return customer.getOrders().stream()
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }
}

