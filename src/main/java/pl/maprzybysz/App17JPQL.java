package pl.maprzybysz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.maprzybysz.entity.Product;
import pl.maprzybysz.entity.Review;
import pl.maprzybysz.entity.ReviewDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class App17JPQL {

    private static Logger logger = LogManager.getLogger(App17JPQL.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");


    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
/*
        TypedQuery<Product> query = em.createQuery("select p from Product p order by p.id DESC", Product.class);
        TypedQuery<Product> query = em.createQuery("select p from Product p where p.name like '%04'", Product.class);
        List<Product> resultList = query.getResultList();
        for (Product product : resultList) {
           logger.info(product);
       }
 */

 /*
        TypedQuery<Product> query = em.createQuery("select p from Product p where p.id=:id", Product.class);
        query.setParameter("id", 45L);
        try{
            Product product = query.getSingleResult();
            logger.info(product);
        }catch (NoResultException e){
           logger.error(e);
        }

*/
/*        Product product = query.getResultStream().findFirst().orElseThrow(()-> new RuntimeException("Brak wynikow"));
        logger.info(product);

*/
/*      Query query = em.createQuery("select AVG(p.price) from Product p");
        Double singleResult = (Double) query.getSingleResult();
        logger.info(singleResult);
*/
/*      Query query = em.createQuery("select COUNT(p), AVG(p.price) from Product p");

        Object[] res = (Object[]) query.getSingleResult();
        logger.info(res[0]+", "+res[1]);
*/
        Query query = em.createQuery("select p.category.id, COUNT(p) from Product p group by p.category");
        List<Object[]> resultList = query.getResultList();
        for (Object[] array : resultList) {
            logger.info(array[0]+", "+array[1]);
        }
        em.getTransaction().commit();
        em.close();


    }
}
