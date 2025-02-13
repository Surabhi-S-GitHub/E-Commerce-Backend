package ecommerce.project.ecommerce.com.surabhi.service;

import ecommerce.project.ecommerce.com.surabhi.Exception.ProductException;
import ecommerce.project.ecommerce.com.surabhi.model.Cart;
import ecommerce.project.ecommerce.com.surabhi.model.CartItem;
import ecommerce.project.ecommerce.com.surabhi.model.User;
import ecommerce.project.ecommerce.com.surabhi.request.AddItemRequest;

public interface CartService {
	
	public Cart createCart(User user);
	
	public CartItem addCartItem(Long userId,AddItemRequest req) throws ProductException;
	
	public Cart findUserCart(Long userId);

}
