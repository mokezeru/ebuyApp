package cs544.project.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Entity
@Table(name="PRODUCT")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Positive(message = "Should be Positive")
    @NotNull(message = "Required!")
    private Long productNumber;
    @NotEmpty(message = "Required!")
    private String name;
    @NotEmpty(message = "Required!")
    private String description;

    @NotNull(message = "Required!")
    @Positive(message = "Should be Positive")
    private double price;
    @NotEmpty(message = "Required!")
    private String phoneNumber;
    @NotEmpty(message = "Required!")
    private String image;
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User user;



    public Product(){

    }
    public Product(Long productNumber,String name, String description, double price, String phoneNumber, String image) {
        this.productNumber=productNumber;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        //this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(Long productNumber) {
        this.productNumber = productNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
