package ecommerce.project.ecommerce.com.surabhi.request;

import java.util.HashSet;
import java.util.Set;

import ecommerce.project.ecommerce.com.surabhi.model.Size;

public class CreateProductRequest {
    private String title;
    private String description;
    private int price;
    private int discountedPrice;
    private int discountedPercent;
    private int quantity;
    private String brand;
    private String color;

    private Set<Size>size=new HashSet<>();
    private String imageURL;
    private String topLevelCategory;
    private String secondLevelCategory;
    private String thirdLevelCategory;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public int getDiscountedPrice() {
        return discountedPrice;
    }
    public void setDiscountedPrice(int discountedPrice) {
        this.discountedPrice = discountedPrice;
    }
    public int getDiscountedPercent() {
        return discountedPercent;
    }
    public void setDiscountedPercent(int discountedPercent) {
        this.discountedPercent = discountedPercent;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public Set<Size> getSize() {
        return size;
    }
    public void setSize(Set<Size> size) {
        this.size = size;
    }
    public String getImageURL() {
        return imageURL;
    }
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
    public String getTopLevelCategory() {
        return topLevelCategory;
    }
    public void setTopLevelCategory(String topLevelCategory) {
        this.topLevelCategory = topLevelCategory;
    }
    public String getSecondLevelCategory() {
        return secondLevelCategory;
    }
    public void setSecondLevelCategory(String secondLevelCategory) {
        this.secondLevelCategory = secondLevelCategory;
    }
    public String getThirdLevelCategory() {
        return thirdLevelCategory;
    }
    public void setThirdLevelCategory(String thirdLevelCategory) {
        this.thirdLevelCategory = thirdLevelCategory;
    }

    
}
