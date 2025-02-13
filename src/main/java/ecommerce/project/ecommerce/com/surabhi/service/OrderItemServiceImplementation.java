package ecommerce.project.ecommerce.com.surabhi.service;

import org.springframework.stereotype.Service;

import ecommerce.project.ecommerce.com.surabhi.model.OrderItem;
import ecommerce.project.ecommerce.com.surabhi.repository.OrderItemRepository;

@Service
public class OrderItemServiceImplementation implements OrderItemService {

	private OrderItemRepository orderItemRepository;
	public OrderItemServiceImplementation(OrderItemRepository orderItemRepository) {
		this.orderItemRepository=orderItemRepository;
	}
	@Override
	public OrderItem createOrderItem(OrderItem orderItem) {
		
		return orderItemRepository.save(orderItem);
	}

}
