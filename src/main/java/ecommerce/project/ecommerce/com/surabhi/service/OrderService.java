package ecommerce.project.ecommerce.com.surabhi.service;

import java.util.List;

import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

import ecommerce.project.ecommerce.com.surabhi.Exception.OrderException;
import ecommerce.project.ecommerce.com.surabhi.model.Address;
import ecommerce.project.ecommerce.com.surabhi.model.User;

public interface OrderService {
  public Order createOrder(User user,Address shippingAddress);
  public Order findOrder(Long orderId) throws OrderException;
  public List<Order>usesOrderHistory(Long userId);
  public Order placedOrder(Long orderId) throws OrderException;
  public Order confirmedOrder(Long orderId) throws OrderException;
  public Order shippedOrder(Long orderId) throws OrderException;
  public Order deliveredOrder(Long orderId) throws OrderException;
  public Order canceledOrder(Long orderId) throws OrderException;

}
