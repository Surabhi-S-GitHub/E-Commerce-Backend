package ecommerce.project.ecommerce.com.surabhi.Exception;

public class ProductException extends RuntimeException { // Extends RuntimeException
    public ProductException(String message) {
        super(message); // Calls the constructor of RuntimeException
    }
}
