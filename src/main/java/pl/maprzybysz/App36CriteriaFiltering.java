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
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class App36CriteriaFiltering {

    private static Logger logger = LogManager.getLogger(App36CriteriaFiltering.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");


    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> customerRoot = criteriaQuery.from(Customer.class);
       Join<Object, Object> orders = (Join<Object, Object>) customerRoot.fetch("orders", JoinType.INNER);

       ParameterExpression<BigDecimal> total = criteriaBuilder.parameter(BigDecimal.class);
       ParameterExpression<String> lastname = criteriaBuilder.parameter(String.class);
       ParameterExpression<Collection> ids = criteriaBuilder.parameter(Collection.class);
        criteriaQuery.select(customerRoot).distinct(true)
        .where(
                criteriaBuilder.and(
//                        criteriaBuilder.or(
//                                criteriaBuilder.like(customerRoot.get("lastname"), criteriaBuilder.concat(lastname,
//                                        "%"))
//                        ),
                        customerRoot.get("id").in(ids),
                        criteriaBuilder.between(orders.get("total"), total, criteriaBuilder.literal(new BigDecimal(
                                "70.00"))),
                        criteriaBuilder.isNotNull(customerRoot.get("firstname"))
                )

        );
        TypedQuery<Customer> query = em.createQuery(criteriaQuery);
        query.setParameter(total, new BigDecimal("30.00"));
        //query.setParameter(lastname, "Kow");
        query.setParameter(ids, Arrays.asList(1L, 2L, 3L));
        List<Customer> resultList = query.getResultList();
        for (Customer customer : resultList) {
            logger.info(customer);
            logger.info(customer.getOrders());
        }


        em.getTransaction().commit();
        em.close();


    }
}

