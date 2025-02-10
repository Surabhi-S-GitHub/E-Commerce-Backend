package ecommerce.project.ecommerce.com.surabhi.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import ecommerce.project.ecommerce.com.surabhi.Exception.ProductException;
import ecommerce.project.ecommerce.com.surabhi.model.Product;
import ecommerce.project.ecommerce.com.surabhi.model.Rating;
import ecommerce.project.ecommerce.com.surabhi.model.User;
import ecommerce.project.ecommerce.com.surabhi.repository.RatingRepository;
import ecommerce.project.ecommerce.com.surabhi.request.RatingRequest;

@Service
public class RatingServiceImplementation implements RatingService {

    private final RatingRepository ratingRepository;
    private final ProductService productService;

    // Constructor-based Dependency Injection
    public RatingServiceImplementation(RatingRepository ratingRepository, ProductService productService) {
        this.ratingRepository = ratingRepository;
        this.productService = productService;
    }

    @Override
    public Rating createRating(RatingRequest req, User user) throws ProductException {
        Product product = productService.findProduct(req.getProductId());

        Rating rating = new Rating();
        rating.setProduct(product);
        rating.setUser(user);
        rating.setRating(req.getRating());
        rating.setCreatedAt(LocalDateTime.now());

        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getProductsRating(Long productId) {
        return ratingRepository.getAllProductsRating(productId);
    }

    
}
