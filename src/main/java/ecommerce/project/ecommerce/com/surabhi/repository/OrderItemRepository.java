package ecommerce.project.ecommerce.com.surabhi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ecommerce.project.ecommerce.com.surabhi.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
