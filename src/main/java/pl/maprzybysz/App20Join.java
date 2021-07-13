package pl.maprzybysz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.maprzybysz.entity.Product;
import pl.maprzybysz.entity.ProductInCategoryCounterDTO;

import javax.persistence.*;
import java.util.List;

public class App20Join {

    private static Logger logger = LogManager.getLogger(App20Join.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");


    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

      /*  TypedQuery<Product> query = em.createQuery("select p from Product p inner join fetch p.category c " +
                "where c.id=:id", Product.class);
                query.setParameter("id", 3L);
       */
        TypedQuery<Product> query = em.createQuery("select p from Product p left join fetch p.category c ",
                Product.class);


        List<Product> resultList = query.getResultList();
        for (Product product : resultList) {
            logger.info(product);
            logger.info(product.getCategory());
        }


        em.getTransaction().commit();
        em.close();


    }
}
