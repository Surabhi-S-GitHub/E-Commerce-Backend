package ecommerce.project.ecommerce.com.surabhi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ecommerce.project.ecommerce.com.surabhi.model.Reviews;

public interface ReviewsRepository extends JpaRepository<Reviews, Long>{
    
    @Query("SELECT r FROM Reviews r WHERE r.product.id=:productId")
    public List<Reviews>getAllProductsReview(@Param("productId")Long productId);
}
