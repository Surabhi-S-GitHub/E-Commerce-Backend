package ecommerce.project.ecommerce.com.surabhi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ecommerce.project.ecommerce.com.surabhi.model.Cart;
import ecommerce.project.ecommerce.com.surabhi.model.CartItem;
import ecommerce.project.ecommerce.com.surabhi.model.Product;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{
    

    @Query("SELECT ci FROM CartItem ci WHERE ci.cart=:cart AND ci.product=:product AND ci.size=:size AND ci.userId=:userId")
    public static CartItem isCardItemExist(@Param("cart")Cart cart,
    @Param("product")Product product,
    @Param("size")String size,
    @Param("userId")Long userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isCardItemExist'");
    }

}
