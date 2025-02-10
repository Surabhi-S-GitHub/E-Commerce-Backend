package ecommerce.project.ecommerce.com.surabhi.service;

import java.util.Optional;

import ecommerce.project.ecommerce.com.surabhi.Exception.CartItemException;
import ecommerce.project.ecommerce.com.surabhi.Exception.UserException;
import ecommerce.project.ecommerce.com.surabhi.model.Cart;
import ecommerce.project.ecommerce.com.surabhi.model.CartItem;
import ecommerce.project.ecommerce.com.surabhi.model.Product;

public interface CartItemService {
    CartItem createCartItem(CartItem cartItem);

    CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException;

    Optional<CartItem> findExistingCartItem(Cart cart, Product product, String size, Long userId);

    void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException;

    CartItem findCartItemById(Long cartItemId) throws CartItemException;

    CartItem isCartItemExist(Cart cart, Product product, String size, Long userId);

}
