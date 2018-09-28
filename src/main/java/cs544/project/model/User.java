package cs544.project.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // private Long userNumber;
    @NotEmpty(message = "Required!")
    private String name;
    @NotEmpty(message = "Required!")
    private String phone;
    
    private String role;
    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<Product> products;

    public User() {
    }

    public User(String name, String phone) {
        this.name = name;
        this.phone = phone;
        //this.userNumber = userNumber;
    }
    
    public User(User u) {
    	this.name = u.getName();
    	this.phone = u.getPhone();
    	this.role = u.getRole();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   /* public Long getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(Long userNumber) {
        this.userNumber = userNumber;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setRole(String role) {
    	this.role=role;
    }
    public String getRole() {
    	return this.role;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
