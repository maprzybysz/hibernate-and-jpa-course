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

public class App18JPQLResponseMapping {

    private static Logger logger = LogManager.getLogger(App18JPQLResponseMapping.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");


    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("select p.category.id, COUNT(p) from Product p group by p.category");
        List<Object[]> resultList = query.getResultList();
        List<ProductInCategoryCounterDTO> result = resultList.stream()
                .map(objects -> new ProductInCategoryCounterDTO((Long) objects[0], (Long) objects[1]))
                .collect(Collectors.toList());

        for (ProductInCategoryCounterDTO productInCategoryCounterDTO : result) {
            logger.info("category id: "+productInCategoryCounterDTO.getCategoryId()+" counter: "+productInCategoryCounterDTO.getProductInCategoryCounter());

        }

        em.getTransaction().commit();
        em.close();


    }
}
