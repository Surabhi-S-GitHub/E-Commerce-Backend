package ecommerce.project.ecommerce.com.surabhi.service;

import java.util.List;

import ecommerce.project.ecommerce.com.surabhi.Exception.ProductException;
import ecommerce.project.ecommerce.com.surabhi.model.Review;
import ecommerce.project.ecommerce.com.surabhi.model.User;
import ecommerce.project.ecommerce.com.surabhi.request.ReviewRequest;

public interface ReviewService {

	public Review createReview(ReviewRequest req,User user) throws ProductException;
	
	public List<Review> getAllReview(Long productId);
	
	
}
