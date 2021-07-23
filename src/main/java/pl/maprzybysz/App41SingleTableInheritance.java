package pl.maprzybysz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.maprzybysz.entity.Order;
import pl.maprzybysz.entity.RealProduct;
import pl.maprzybysz.entity.VirtualProduct;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class App41SingleTableInheritance {

    private static Logger logger = LogManager.getLogger(App41SingleTableInheritance.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");


    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        VirtualProduct virtualProduct = new VirtualProduct();
        virtualProduct.setName("Virtual");
        virtualProduct.setDescription("Desc virtual");
        virtualProduct.setCreated(LocalDateTime.now());
        virtualProduct.setDownloadable(true);
        virtualProduct.setFileName("test.txt");
        virtualProduct.setFilePath("/store");
        em.persist(virtualProduct);

        RealProduct realProduct = new RealProduct();
        realProduct.setName("Real");
        realProduct.setDescription("Desc real");
        realProduct.setCreated(LocalDateTime.now());
        realProduct.setWeight(10.5f);
        realProduct.setWidth(20);
        realProduct.setLength(10);
        realProduct.setHeight(30);
        em.persist(realProduct);

        em.flush();
        em.clear();

        em.find(VirtualProduct.class, virtualProduct.getId());
        em.find(RealProduct.class, realProduct.getId());

        em.getTransaction().commit();
        em.close();


    }
}

