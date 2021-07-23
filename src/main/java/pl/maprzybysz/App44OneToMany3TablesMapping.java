package pl.maprzybysz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.maprzybysz.entity.Customer;
import pl.maprzybysz.entity.Note;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class App44OneToMany3TablesMapping {
    private static Logger logger = LogManager.getLogger(App44OneToMany3TablesMapping.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();


        Customer customer = em.find(Customer.class, 1L);
        customer.getNotes().add(new Note("Content 1", LocalDateTime.now()));
        customer.getNotes().add(new Note("Content 2", LocalDateTime.now()));
        customer.getNotes().add(new Note("Content 3", LocalDateTime.now()));
        customer.getNotes().add(new Note("Content 4", LocalDateTime.now()));


        em.getTransaction().commit();
        em.close();
    }
}

