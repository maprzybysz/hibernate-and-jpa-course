package pl.maprzybysz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.maprzybysz.entity.ProductInCategoryCounterDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

public class App19JpqlAutomaticResponseMapping {

    private static Logger logger = LogManager.getLogger(App19JpqlAutomaticResponseMapping.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");


    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery(
                "select new pl.maprzybysz.entity.ProductInCategoryCounterDTO(p.category.id, COUNT(p)) from Product p " +
                        "group by p.category"
        );

        List<ProductInCategoryCounterDTO> resultList = query.getResultList();
        for (ProductInCategoryCounterDTO productInCategoryCounterDTO : resultList) {
            logger.info(productInCategoryCounterDTO.getCategoryId());
            logger.info(productInCategoryCounterDTO.getProductInCategoryCounter());
        }



        em.getTransaction().commit();
        em.close();


    }
}
