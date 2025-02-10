package ecommerce.project.ecommerce.com.surabhi.service;

import org.springframework.stereotype.Service;

import ecommerce.project.ecommerce.com.surabhi.Exception.ProductException;
import ecommerce.project.ecommerce.com.surabhi.model.Cart;
import ecommerce.project.ecommerce.com.surabhi.model.CartItem;
import ecommerce.project.ecommerce.com.surabhi.model.Product;
import ecommerce.project.ecommerce.com.surabhi.model.User;
import ecommerce.project.ecommerce.com.surabhi.repository.CartRepository;
import ecommerce.project.ecommerce.com.surabhi.request.AddItemRequest;

@Service
public class CartServiceImplementation implements CartService {

    private final CartRepository cartRepository;
    private final CartItemService cartItemService;
    private final ProductService productService;

    public CartServiceImplementation(CartRepository cartRepository, CartItemService cartItemService,
                                     ProductService productService) {
        this.cartRepository = cartRepository;
        this.cartItemService = cartItemService;
        this.productService = productService;
    }

    @Override
    public Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    @Override
    public String addCartItem(Long userId, AddItemRequest req) throws ProductException {
        Cart cart = cartRepository.findUserById(userId);
        if (cart == null) {
            throw new RuntimeException("Cart not found for user ID: " + userId);
        }

        Product product = productService.findProduct(req.getProductId());
        CartItem isPresent = cartItemService.isCartItemExist(cart, product, req.getSize(), userId);

        if (isPresent == null) {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItem.setQuantity(req.getQuantity());
            cartItem.setUserId(userId);
            cartItem.setSize(req.getSize());

            int price = req.getQuantity() * product.getDiscountedprice();
            cartItem.setPrice(price);

            CartItem createdCartItem = cartItemService.createCartItem(cartItem);
            cart.getCartItems().add(createdCartItem);
            cartRepository.save(cart); // Save updated cart
        }

        return "Item Added to Cart";
    }

    @Override
    public Cart findUserCart(Long userId) {
        Cart cart = cartRepository.findUserById(userId);
        if (cart == null) {
            throw new RuntimeException("Cart not found for user ID: " + userId);
        }

        int totalPrice = 0;
        int totalDiscountedPrice = 0;
        int totalItem = 0;

        for (CartItem cartItem : cart.getCartItems()) {
            totalPrice += cartItem.getPrice();
            totalDiscountedPrice += cartItem.getProduct().getDiscountedprice() * cartItem.getQuantity();
            totalItem += cartItem.getQuantity();
        }

        cart.setTotalDiscountedPrice(totalDiscountedPrice);
        cart.setTotalItem(totalItem);
        cart.setTotalPrice(totalPrice);
        cart.setDiscount(totalPrice - totalDiscountedPrice);

        return cartRepository.save(cart);
    }
}
