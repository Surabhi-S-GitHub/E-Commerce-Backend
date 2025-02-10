package ecommerce.project.ecommerce.com.surabhi.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import ecommerce.project.ecommerce.com.surabhi.Exception.ProductException;
import ecommerce.project.ecommerce.com.surabhi.model.Product;
import ecommerce.project.ecommerce.com.surabhi.model.Reviews;
import ecommerce.project.ecommerce.com.surabhi.model.User;
import ecommerce.project.ecommerce.com.surabhi.repository.ProductRepository;
import ecommerce.project.ecommerce.com.surabhi.repository.ReviewsRepository;
import ecommerce.project.ecommerce.com.surabhi.request.ReviewRequest;

@Service
public class ReviewsServiceImplementation implements ReviewsService{
    private ReviewsRepository reviewsRepository;
    private ProductService productService;
    private ProductRepository productRepository;
    



    public ReviewsServiceImplementation(ReviewsRepository reviewsRepository, ProductService productService,
            ProductRepository productRepository) {
        this.reviewsRepository = reviewsRepository;
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @Override
    public Reviews createReviews(ReviewRequest req, User user) throws ProductException {
        Product product=productService.findProductById(req.getProductId());

        Reviews reviews=new Reviews();
        reviews.setUser(user);
        reviews.setProduct(product);
        reviews.setReview(req.getReview());
        reviews.setCreatedAt(LocalDateTime.now());
        
        return reviewsRepository.save(reviews);
    }

    @Override
    public List<Reviews> getAllReview(Long productId) {
        return reviewsRepository.getAllProductsReview(productId);
    }
    
}
