package pl.maprzybysz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.annotations.QueryHints;
import pl.maprzybysz.entity.Category;
import pl.maprzybysz.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class App22CartesianProduct {

    private static Logger logger = LogManager.getLogger(App22CartesianProduct.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");


    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();


        /*TypedQuery<Product> query = em.createQuery("select p from Product p" +
                " left join fetch p.attributes" +
                " left join fetch p.reviews", Product.class);
           List<Product> resultList = query.getResultList();
         */
        List<Product> resultList = em.createQuery("select distinct p from Product p" +
                " left join fetch p.attributes"
                , Product.class)
                .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                .getResultList();
        resultList = em.createQuery("select distinct p from Product p" +
                        " left join fetch p.reviews"
                , Product.class)
                .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                .getResultList();

        logger.info("Size: "+resultList.size());
        for (Product product : resultList) {
            logger.info(product);
            logger.info(product.getAttributes());
            logger.info(product.getReviews());
        }


        em.getTransaction().commit();
        em.close();


    }
}
