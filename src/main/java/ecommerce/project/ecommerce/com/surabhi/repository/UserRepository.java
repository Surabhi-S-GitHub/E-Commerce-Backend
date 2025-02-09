package ecommerce.project.ecommerce.com.surabhi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ecommerce.project.ecommerce.com.surabhi.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
  public User findByEmail(String email);
}
