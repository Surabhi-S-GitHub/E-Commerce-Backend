package ecommerce.project.ecommerce.com.surabhi.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ecommerce.project.ecommerce.com.surabhi.Exception.ProductException;
import ecommerce.project.ecommerce.com.surabhi.model.Category;
import ecommerce.project.ecommerce.com.surabhi.model.Product;
import ecommerce.project.ecommerce.com.surabhi.repository.CategoryRepository;
import ecommerce.project.ecommerce.com.surabhi.repository.ProductRepository;
import ecommerce.project.ecommerce.com.surabhi.request.CreateProductRequest;

@Service
public class ProductServiceImplementation implements ProductService {

    private ProductRepository productRepository;
    private UserService userService;
    private CategoryRepository categoryRepository;

    public ProductServiceImplementation(ProductRepository productRepository, UserService userService,
            CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product createProduct(CreateProductRequest req) {
        Category topLevel = categoryRepository.findByName(req.getTopLevelCategory());

        if (topLevel == null) {
            Category topLevelCategory = new Category();
            topLevelCategory.setName(req.getTopLevelCategory());
            topLevelCategory.setLevel(1);

            topLevel = categoryRepository.save(topLevelCategory);
        }

        Category secondLevel = categoryRepository.findByNameAndParent(req.getSecondLevelCategory(), topLevel.getName());

        if (secondLevel == null) {
            Category secondLevelCategory = new Category();
            secondLevelCategory.setName(req.getSecondLevelCategory());
            secondLevelCategory.setParenCategory(topLevel);
            secondLevelCategory.setLevel(2);

            secondLevel = categoryRepository.save(secondLevelCategory);
        }

        Category thirdLevel = categoryRepository.findByNameAndParent(req.getThirdLevelCategory(), secondLevel.getName());

        if (thirdLevel == null) {
            Category thirdLevelCategory = new Category();
            thirdLevelCategory.setName(req.getThirdLevelCategory());
            thirdLevelCategory.setParenCategory(secondLevel);
            thirdLevelCategory.setLevel(3);

            thirdLevel = categoryRepository.save(thirdLevelCategory);
        }

        Product p = new Product();
        p.setTitle(req.getTitle());
        p.setColor(req.getColor());
        p.setDescription(req.getDescription());
        p.setDiscountedpercent(req.getDiscountedPercent());
        p.setDiscountedprice(req.getDiscountedPrice());
        p.setBrand(req.getBrand());
        p.setImageurl(req.getImageURL());
        p.setPrice(req.getPrice());
        p.setSizes(req.getSize());
        p.setQuantity(req.getQuantity());
        p.setCategory(thirdLevel);
        p.setCreatedAt(LocalDateTime.now());

        return productRepository.save(p);
    }

    @Override
    public String deleteProduct(Long productId) throws ProductException {
        Product p = findProduct(productId);
        p.getSizes().clear();
        productRepository.delete(p);
        return "Product Deleted Successfully";
    }

   

    @Override
    public Product updateProduct(Long productId, Product req) throws ProductException {
        Product p = findProduct(productId);

        if (req.getQuantity() != 0) {
            p.setQuantity(req.getQuantity());
        }
        return productRepository.save(p);
    }


    @Override
    public Product findProduct(Long productId) throws ProductException {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductException("Product Not found with id-" + productId));
    }

   

    @Override
    public Page<Product> getAllProducts(String category, List<String> colors, List<String> sizes, Integer minPrice,
            Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {
                Pageable pageable = PageRequest.of(pageNumber, pageSize);
                List<Product> products = productRepository.filterProduct(category, minPrice, maxPrice, minDiscount,sort);
        
                if (colors != null && !colors.isEmpty()) {
                    products = products.stream()
                            .filter(p -> colors.stream().anyMatch(c -> c.equalsIgnoreCase(p.getColor())))
                            .collect(Collectors.toList());
                }
        
                if (stock != null) {
                    if ("in_stock".equals(stock)) {
                        products = products.stream().filter(p -> p.getQuantity() > 0).collect(Collectors.toList());
                    } else if ("out_of_stock".equals(stock)) {
                        products = products.stream().filter(p -> p.getQuantity() < 1).collect(Collectors.toList());
                    }
                }
        
                int startIndex = (int) pageable.getOffset();
                int endIndex = Math.min(startIndex + pageable.getPageSize(), products.size());
        
                List<Product> pageContent = products.subList(startIndex, endIndex);
                return new PageImpl<>(pageContent, pageable, products.size());
    }

    @Override
    public List<Product> findProductById(String category) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findProductById'");
    }

    @Override
    public Product findProductById(Long productId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findProductById'");
    }
}
