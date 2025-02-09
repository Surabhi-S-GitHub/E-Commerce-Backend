package ecommerce.project.ecommerce.com.surabhi.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import ecommerce.project.ecommerce.com.surabhi.Exception.UserException;
import ecommerce.project.ecommerce.com.surabhi.config.JwtProvider;
import ecommerce.project.ecommerce.com.surabhi.model.User;
import ecommerce.project.ecommerce.com.surabhi.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public UserServiceImplementation(UserRepository userRepository, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public User findUserById(Long userId) throws UserException {
        Optional<User> user = userRepository.findById(userId); // Fixed incorrect static call
        return user.orElseThrow(() -> new UserException("User Not found with id - " + userId));
    }

    @Override
    public User findUserByProfileByJwt(String jwt) throws UserException { // Fixed method name typo
        String email = jwtProvider.getEmailFromToken(jwt);
        User user = userRepository.findByEmail(email); // Fixed incorrect static call
        if (user == null) {
            throw new UserException("User not found with email - " + email);
        }
        return user;
    }
}
