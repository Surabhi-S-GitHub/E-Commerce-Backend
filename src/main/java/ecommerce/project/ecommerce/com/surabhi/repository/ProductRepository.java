package ecommerce.project.ecommerce.com.surabhi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ecommerce.project.ecommerce.com.surabhi.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

  @Query("SELECT p FROM Product p " +
       "WHERE (:category IS NULL OR p.category.name = :category) " +
       "AND (:minPrice IS NULL OR :maxPrice IS NULL OR p.discountedprice BETWEEN :minPrice AND :maxPrice) " +
       "AND (:minDiscount IS NULL OR p.discountedpercent >= :minDiscount) " +
       "ORDER BY " +
       "CASE WHEN :sort = 'price_low' THEN p.discountedprice END ASC, " +
       "CASE WHEN :sort = 'price_high' THEN p.discountedprice END DESC, " +
       "p.id ASC") 

  public List<Product> filterProduct(
            @Param("category") String category,
            @Param("minPrice") Integer minPrice,
            @Param("maxPrice") Integer maxPrice,
            @Param("minDiscount") Integer minDiscount, // Added missing param
            @Param("sort") String sort);

}
