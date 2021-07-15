package pl.maprzybysz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.annotations.QueryHints;
import pl.maprzybysz.entity.batch.BatchReview;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.stream.Stream;

public class App31UpdateScrolling {

    private static Logger logger = LogManager.getLogger(App31UpdateScrolling.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");


    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

            em.createQuery("select r from BatchReview r", BatchReview.class)
                    .setHint(QueryHints.FETCH_SIZE, Integer.MIN_VALUE)
                    .getResultStream()
                    .forEach(batchReview -> {
                        batchReview.setContent("new");
                        batchReview.setRating(5);
                        logger.info(batchReview);
                    });


        em.getTransaction().commit();
        em.close();


    }
}

