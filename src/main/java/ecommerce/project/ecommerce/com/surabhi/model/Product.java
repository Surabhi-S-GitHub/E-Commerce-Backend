package ecommerce.project.ecommerce.com.surabhi.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Product {
  @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private int price;
    @Column(name="discounted_price")
    private Integer discountedprice;
    @Column(name="discounted_percent")
    private Integer discountedpercent;
    @Column(name="quantity")
    private Integer quantity;
    @Column(name="brand")
    private String brand;    
    @Column(name="color")
    private String color;

   @Embedded
   @ElementCollection
   @Column(name="sizes")
   private Set<Size> sizes=new HashSet<>();

   @Column(name="image_url")
    private String imageurl;
    
    @OneToMany(mappedBy="product",cascade=CascadeType.ALL,orphanRemoval=true)  
    private List<Rating> ratings=new ArrayList<>();

    @OneToMany(mappedBy="product",cascade=CascadeType.ALL,orphanRemoval=true)  
    private List<Reviews> reviews=new ArrayList<>();

    @Column(name="num_ratings")
    private int numratings;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    private LocalDateTime createdAt;

    public Product(){

    }

    public Product(Long id, String title, String description, int price, int discountedprice, int discountedpercent,
        int quantity, String brand, String color, Set<Size> sizes, String imageurl, List<Rating> ratings,
        List<Reviews> reviews, int numratings, Category category, LocalDateTime createdAt) {
      this.id = id;
      this.title = title;
      this.description = description;
      this.price = price;
      this.discountedprice = discountedprice;
      this.discountedpercent = discountedpercent;
      this.quantity = quantity;
      this.brand = brand;
      this.color = color;
      this.sizes = sizes;
      this.imageurl = imageurl;
      this.ratings = ratings;
      this.reviews = reviews;
      this.numratings = numratings;
      this.category = category;
      this.createdAt = createdAt;
    }

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

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

    public int getDiscountedprice() {
      return discountedprice;
    }

    public void setDiscountedprice(int discountedprice) {
      this.discountedprice = discountedprice;
    }

    public int getDiscountedpercent() {
      return discountedpercent;
    }

    public void setDiscountedpercent(int discountedpercent) {
      this.discountedpercent = discountedpercent;
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

    public Set<Size> getSizes() {
      return sizes;
    }

    public void setSizes(Set<Size> sizes) {
      this.sizes = sizes;
    }

    public String getImageurl() {
      return imageurl;
    }

    public void setImageurl(String imageurl) {
      this.imageurl = imageurl;
    }

    public List<Rating> getRatings() {
      return ratings;
    }

    public void setRatings(List<Rating> ratings) {
      this.ratings = ratings;
    }

    public List<Reviews> getReviews() {
      return reviews;
    }

    public void setReviews(List<Reviews> reviews) {
      this.reviews = reviews;
    }

    public int getNumratings() {
      return numratings;
    }

    public void setNumratings(int numratings) {
      this.numratings = numratings;
    }

    public Category getCategory() {
      return category;
    }

    public void setCategory(Category category) {
      this.category = category;
    }

    public LocalDateTime getCreatedAt() {
      return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
      this.createdAt = createdAt;
    }
    
}
