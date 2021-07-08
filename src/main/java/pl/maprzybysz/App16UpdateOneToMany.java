package pl.maprzybysz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.maprzybysz.entity.Attribute;
import pl.maprzybysz.entity.Product;
import pl.maprzybysz.entity.Review;
import pl.maprzybysz.entity.ReviewDto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class App16UpdateOneToMany {

    private static Logger logger = LogManager.getLogger(App16UpdateOneToMany.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");


    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();

        List<ReviewDto> reviewDtos = getUpdatedReviews();

        Product product = em.find(Product.class, 5L);
        for (Review review : product.getReviews()) {
            for (ReviewDto reviewDto : reviewDtos) {
                if(review.getId().equals(reviewDto.getId())){
                    review.setContent(reviewDto.getContent());
                    review.setRating(reviewDto.getRating());
                }
            }
        }


        em.getTransaction().commit();
        em.close();
    }

    private static List<ReviewDto> getUpdatedReviews(){
        List<ReviewDto> list = new ArrayList<>();
        list.add(new ReviewDto(25L, "Opinia100", 10));
        list.add(new ReviewDto(26L, "Opinia100", 10));
        list.add(new ReviewDto(27L, "Opinia100", 10));
        return list;
    }
}
