package pl.maprzybysz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.maprzybysz.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.List;

public class App35CriteriaJoin {

    private static Logger logger = LogManager.getLogger(App35CriteriaJoin.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");


    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> customerRoot = criteriaQuery.from(Customer.class);
       Join<Object, Object> orders = (Join<Object, Object>) customerRoot.fetch("orders", JoinType.INNER);
        ParameterExpression<Long> id = criteriaBuilder.parameter(Long.class);
        ParameterExpression<BigDecimal> total = criteriaBuilder.parameter(BigDecimal.class);
        criteriaQuery.select(customerRoot).distinct(true)
        .where(
                criteriaBuilder.and(criteriaBuilder.equal(customerRoot.get("id"), id)),
                criteriaBuilder.greaterThan(orders.get("total"), total)

        );
        TypedQuery<Customer> query = em.createQuery(criteriaQuery);
        query.setParameter(id, 1L);
        query.setParameter(total, new BigDecimal("50.00"));
        List<Customer> resultList = query.getResultList();
        for (Customer customer : resultList) {
            logger.info(customer);
            customer.getOrders().forEach(order -> {
                logger.info(order);
                order.getOrderRows().forEach(orderRow -> {
                    logger.info(orderRow);
                    logger.info(orderRow.getProduct());
                });
            });
        }


        em.getTransaction().commit();
        em.close();


    }
}

