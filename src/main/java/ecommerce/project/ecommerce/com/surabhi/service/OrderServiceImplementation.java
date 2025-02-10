package ecommerce.project.ecommerce.com.surabhi.service;

import java.util.List;

import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

import ecommerce.project.ecommerce.com.surabhi.Exception.OrderException;
import ecommerce.project.ecommerce.com.surabhi.model.Address;
import ecommerce.project.ecommerce.com.surabhi.model.User;
import ecommerce.project.ecommerce.com.surabhi.repository.CartRepository;

public class OrderServiceImplementation implements OrderService{

    private CartRepository cartRepository;
    private CartService cartItemService;
    private ProductService productService;
    

    public OrderServiceImplementation(CartRepository cartRepository, CartService cartItemService,
            ProductService productService) {
        this.cartRepository = cartRepository;
        this.cartItemService = cartItemService;
        this.productService = productService;
    }

    @Override
    public Order createOrder(User user, Address shippingAddress) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createOrder'");
    }

    @Override
    public Order findOrder(Long orderId) throws OrderException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findOrder'");
    }

    @Override
    public List<Order> usesOrderHistory(Long userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'usesOrderHistory'");
    }

    @Override
    public Order placedOrder(Long orderId) throws OrderException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'placedOrder'");
    }

    @Override
    public Order confirmedOrder(Long orderId) throws OrderException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'confirmedOrder'");
    }

    @Override
    public Order shippedOrder(Long orderId) throws OrderException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'shippedOrder'");
    }

    @Override
    public Order deliveredOrder(Long orderId) throws OrderException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deliveredOrder'");
    }

    @Override
    public Order canceledOrder(Long orderId) throws OrderException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'canceledOrder'");
    }
    
}
