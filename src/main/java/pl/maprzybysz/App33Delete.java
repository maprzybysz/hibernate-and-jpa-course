package pl.maprzybysz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.maprzybysz.entity.batch.BatchReview;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class App33Delete {

    private static Logger logger = LogManager.getLogger(App33Delete.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");


    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();


        List<BatchReview> reviews = em.createQuery("select r from BatchReview r" +
                " where r.productId=:id", BatchReview.class)
                .setParameter("id", 1L)
                .getResultList();
        for (BatchReview review : reviews) {
            logger.info(review);
            em.remove(review);
        }

        em.getTransaction().commit();
        em.close();


    }
}

