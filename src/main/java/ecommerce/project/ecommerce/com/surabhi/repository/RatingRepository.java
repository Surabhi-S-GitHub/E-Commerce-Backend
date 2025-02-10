package ecommerce.project.ecommerce.com.surabhi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ecommerce.project.ecommerce.com.surabhi.model.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    
    @Query("SELECT r FROM Rating r WHERE r.product.id=:productId")
    public List<Rating> getAllProductsRating(@Param("productId") Long productId);
    
}
