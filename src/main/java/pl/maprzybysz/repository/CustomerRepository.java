package pl.maprzybysz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.maprzybysz.entity.Customer;

import javax.persistence.QueryHint;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("select c from Customer c inner join fetch c.orders where c.id=:id")
    @QueryHints(
            @QueryHint(name = org.hibernate.jpa.QueryHints.HINT_READONLY, value = "true")
    )
    Customer findByIdWithOrders(@Param("id") Long id);

    @Query("delete from Customer c")
    void deleteAllRecords();

}
