package ecommerce.project.ecommerce.com.surabhi.service;

import java.util.List;

import org.springframework.data.domain.Page;

import ecommerce.project.ecommerce.com.surabhi.Exception.ProductException;
import ecommerce.project.ecommerce.com.surabhi.model.Product;
import ecommerce.project.ecommerce.com.surabhi.request.CreateProductRequest;

public interface ProductService {
    Product createProduct(CreateProductRequest req);

    String deleteProduct(Long productId) throws ProductException;

    Product updateProduct(Long productId, Product req) throws ProductException;

    Product findProduct(Long productId) throws ProductException;

    List<Product> findProductByCategory(String category);

    Page<Product> getAllProducts(
        String category, 
        List<String> colors, 
        List<String> sizes, 
        Integer minPrice, 
        Integer maxPrice,
        Integer minDiscount, 
        String sort, 
        String stock, 
        Integer pageNumber, 
        Integer pageSize
    );
}
