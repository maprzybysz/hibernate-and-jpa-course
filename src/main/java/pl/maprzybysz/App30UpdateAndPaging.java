package pl.maprzybysz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import pl.maprzybysz.entity.batch.BatchReview;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class App30UpdateAndPaging {

    private static Logger logger = LogManager.getLogger(App30UpdateAndPaging.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");


    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.unwrap(Session.class).setJdbcBatchSize(10);
        em.getTransaction().begin();

        Long count = em.createQuery("select count(r) from BatchReview r", Long.class).getSingleResult();
        int batchSize = 10;
        em.unwrap(Session.class).setJdbcBatchSize(batchSize);
        for (int i =0; i < count; i+=batchSize){
            List<BatchReview> reviews = em.createQuery("select r from BatchReview r", BatchReview.class)
                    .setFirstResult(0)
                    .setMaxResults(5)
                    .getResultList();
            logger.info(reviews);
            for (BatchReview review : reviews) {
                review.setContent("nowa213 tresc");
                review.setRating(11);
            }
        em.flush();
            em.clear();

        }


        em.getTransaction().commit();
        em.close();


    }
}

