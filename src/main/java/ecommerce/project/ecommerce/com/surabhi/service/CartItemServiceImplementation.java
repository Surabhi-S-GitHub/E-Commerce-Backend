package ecommerce.project.ecommerce.com.surabhi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecommerce.project.ecommerce.com.surabhi.Exception.CartItemException;
import ecommerce.project.ecommerce.com.surabhi.Exception.UserException;
import ecommerce.project.ecommerce.com.surabhi.model.Cart;
import ecommerce.project.ecommerce.com.surabhi.model.CartItem;
import ecommerce.project.ecommerce.com.surabhi.model.Product;
import ecommerce.project.ecommerce.com.surabhi.model.User;
import ecommerce.project.ecommerce.com.surabhi.repository.CartItemRepository;
import ecommerce.project.ecommerce.com.surabhi.repository.CartRepository;

@Service
public class CartItemServiceImplementation implements CartItemService {

    public CartItemRepository getCartItemRepository() {
        return cartItemRepository;
    }

    public UserService getUserService() {
        return userService;
    }

    public CartRepository getCartRepository() {
        return cartRepository;
    }

    private final CartItemRepository cartItemRepository;
    private final UserService userService;
    private final CartRepository cartRepository;

    @Autowired
    public CartItemServiceImplementation(CartItemRepository cartItemRepository, UserService userService,
                                         CartRepository cartRepository) {
        this.cartItemRepository = cartItemRepository;
        this.userService = userService;
        this.cartRepository = cartRepository;
    }

    public CartItemServiceImplementation(CartItemRepository cartItemRepository, CartRepository cartRepository, UserService userService) {
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
        this.userService = userService;
    }

    @Override
public CartItem createCartItem(CartItem cartItem) {

    cartItem.setQuantity(1);
    cartItem.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
    cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedprice() * cartItem.getQuantity());

    CartItem createdCartItem=cartItemRepository.save(cartItem);
    return createdCartItem;

}


    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
        // Implementation needed
        CartItem item=findCartItemById(id);
        User user=userService.findUserById(item.getUserId());
        if (user.getId() == userId) {

            item.setQuantity(cartItem.getQuantity());
            item.setPrice(item.getQuantity()*item.getProduct().getPrice());
            item.setDiscountedPrice(item.getProduct().getDiscountedprice() *item.getQuantity());
        }
        return cartItemRepository.save(item);
    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {
        // Implementation needed
        CartItem cartItem=CartItemRepository.isCardItemExist(cart,product,size,userId);
        return cartItem;
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
        // Implementation needed
        CartItem cartItem=findCartItemById(cartItemId);
        User user=userService.findUserById(cartItem.getUserId());
        User reqUser=userService.findUserById(userId);
        if (user.getId() == reqUser.getId()) { 
            cartItemRepository.deleteById(cartItemId);
        }
        else{
            throw new UserException("You can't remove another users item");
        }
        
    }

    @Override
    public CartItem findCartItemById(Long cartItemId) throws CartItemException {
        // Implementation needed
        Optional<CartItem>opt=cartItemRepository.findById(cartItemId);
        if(opt.isPresent()){
         return opt.get();
        }
        throw new CartItemException("CartItem not found with id: "+cartItemId);
    }

    @Override
    public Optional<CartItem> findExistingCartItem(Cart cart, Product product, String size, Long userId) {
            return null;
      
    }
}
