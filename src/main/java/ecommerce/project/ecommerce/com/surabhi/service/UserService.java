package ecommerce.project.ecommerce.com.surabhi.service;

import ecommerce.project.ecommerce.com.surabhi.Exception.UserException;
import ecommerce.project.ecommerce.com.surabhi.model.User;

public interface UserService {
  public User findUserById(Long userId) throws UserException;
  public User findUserByProfileByJwt(String jwt) throws UserException;
}
