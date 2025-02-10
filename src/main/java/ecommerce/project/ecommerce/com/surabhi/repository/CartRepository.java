package ecommerce.project.ecommerce.com.surabhi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ecommerce.project.ecommerce.com.surabhi.model.Cart;

public interface  CartRepository extends JpaRepository<Cart,Long>{

    @Query("SELECT c FROM Cart c WHERE c.user.id=:userId")
    public Cart findUserById(@Param("userId")Long userId);

}
