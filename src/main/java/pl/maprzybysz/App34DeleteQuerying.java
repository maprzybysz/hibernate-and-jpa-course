package pl.maprzybysz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.maprzybysz.entity.batch.BatchReview;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class App34DeleteQuerying {

    private static Logger logger = LogManager.getLogger(App34DeleteQuerying.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");


    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();


        int update = em.createQuery("delete from Review r where r.product.id=:id")
                .setParameter("id", 2L)
                .executeUpdate();
        logger.info(update);

        em.getTransaction().commit();
        em.close();


    }
}

