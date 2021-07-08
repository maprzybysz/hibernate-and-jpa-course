package pl.maprzybysz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.maprzybysz.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App08OneToOne {

    private static Logger logger = LogManager.getLogger(App08OneToOne.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");


    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();

        Product product = em.find(Product.class, 3L);
        logger.info(product);
        logger.info(product.getCategory());

        em.getTransaction().commit();
        em.close();
    }
}
