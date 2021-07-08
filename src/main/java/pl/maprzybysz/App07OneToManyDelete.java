package pl.maprzybysz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.maprzybysz.entity.Product;
import pl.maprzybysz.entity.Review;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class App07OneToManyDelete {

    private static Logger logger = LogManager.getLogger(App07OneToManyDelete.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");


    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();

        Product product = em.find(Product.class, 1L);
        em.remove(product);

        em.getTransaction().commit();
        em.close();
    }
}
