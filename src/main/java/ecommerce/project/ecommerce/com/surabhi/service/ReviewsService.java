package ecommerce.project.ecommerce.com.surabhi.service;

import java.util.List;

import ecommerce.project.ecommerce.com.surabhi.Exception.ProductException;
import ecommerce.project.ecommerce.com.surabhi.model.Reviews;
import ecommerce.project.ecommerce.com.surabhi.model.User;
import ecommerce.project.ecommerce.com.surabhi.request.ReviewRequest;

public interface ReviewsService {
     public Reviews createReviews(ReviewRequest req,User user) throws ProductException;
     public List<Reviews> getAllReview(Long productId);
}
