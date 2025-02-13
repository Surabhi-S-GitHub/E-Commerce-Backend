package ecommerce.project.ecommerce.com.surabhi.service;

import java.util.List;

import ecommerce.project.ecommerce.com.surabhi.Exception.ProductException;
import ecommerce.project.ecommerce.com.surabhi.model.Rating;
import ecommerce.project.ecommerce.com.surabhi.model.User;
import ecommerce.project.ecommerce.com.surabhi.request.RatingRequest;


public interface RatingServices {
	
	public Rating createRating(RatingRequest req,User user) throws ProductException;
	
	public List<Rating> getProductsRating(Long productId);

}
