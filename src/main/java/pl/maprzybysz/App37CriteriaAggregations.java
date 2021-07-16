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

public class App37CriteriaAggregations {

    private static Logger logger = LogManager.getLogger(App37CriteriaAggregations.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");


    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Customer> customer = criteriaQuery.from(Customer.class);
        Join<Object, Object> orders = customer.join("orders", JoinType.INNER);
        Join<Object, Object> orderRows = orders.join("orderRows");
        Join<Object, Object> product = orderRows.join("product");
        Join<Object, Object> category = product.join("category");


        criteriaQuery.multiselect(
                customer.get("id"),
                customer.get("lastname"),
                category.get("name"),
                criteriaBuilder.sum(orderRows.get("price"))
        ).groupBy(category.get("id"), customer.get("id"))
        .orderBy(criteriaBuilder.desc(criteriaBuilder.sum(orderRows.get("price"))))
        .having(criteriaBuilder.greaterThan(criteriaBuilder.sum(orderRows.get("price")), 50));

        TypedQuery<Object[]> query = em.createQuery(criteriaQuery);
        List<Object[]> resultList = query.getResultList();
        for (Object[] row : resultList) {
            logger.info(row[0]+", "+row[1]+", "+row[2]+", "+row[3]);
        }

        em.getTransaction().commit();
        em.close();


    }
}

