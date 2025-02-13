package ecommerce.project.ecommerce.com.surabhi.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import ecommerce.project.ecommerce.com.surabhi.Exception.ProductException;
import ecommerce.project.ecommerce.com.surabhi.model.Product;
import ecommerce.project.ecommerce.com.surabhi.model.Review;
import ecommerce.project.ecommerce.com.surabhi.model.User;
import ecommerce.project.ecommerce.com.surabhi.repository.ProductRepository;
import ecommerce.project.ecommerce.com.surabhi.repository.ReviewRepository;
import ecommerce.project.ecommerce.com.surabhi.request.ReviewRequest;


@Service
public class ReviewServiceImplementation implements ReviewService {
	
	private ReviewRepository reviewRepository;
	private ProductService productService;
	private ProductRepository productRepository;
	
	public ReviewServiceImplementation(ReviewRepository reviewRepository,ProductService productService,ProductRepository productRepository) {
		this.reviewRepository=reviewRepository;
		this.productService=productService;
		this.productRepository=productRepository;
	}

	@Override
	public Review createReview(ReviewRequest req,User user) throws ProductException {
		// TODO Auto-generated method stub
		Product product=productService.findProductById(req.getProductId());
		Review review=new Review();
		review.setUser(user);
		review.setProduct(product);
		review.setReview(req.getReview());
		review.setCreatedAt(LocalDateTime.now());
		
//		product.getReviews().add(review);
		productRepository.save(product);
		return reviewRepository.save(review);
	}

	@Override
	public List<Review> getAllReview(Long productId) {
		
		return reviewRepository.getAllProductsReview(productId);
	}

}
