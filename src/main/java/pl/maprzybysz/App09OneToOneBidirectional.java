package pl.maprzybysz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.maprzybysz.entity.Category;
import pl.maprzybysz.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App09OneToOneBidirectional {

    private static Logger logger = LogManager.getLogger(App09OneToOneBidirectional.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");


    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();

        Category category = em.find(Category.class, 1L);
        logger.info(category);
        em.remove(category);

        em.getTransaction().commit();
        em.close();
    }
}
