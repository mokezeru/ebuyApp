package cs544.project.controller;

import cs544.project.model.Product;
import cs544.project.model.User;
import cs544.project.service.IProductService;
import cs544.project.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

import java.util.Collection;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private IProductService productService;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String getProducts(@AuthenticationPrincipal final UserDetails userdetails,Model model) {
    	System.out.println("I have logged in successfully");
    	
    	String name = userdetails.getUsername();
    	String phone = userdetails.getPassword();//phone # is considered to be password
    	
//    	System.out.println("the user name is "+name);
//    	System.out.println("the phone num or pw is "+phone);
    	
    	Collection<? extends GrantedAuthority> roles = userdetails.getAuthorities();
		GrantedAuthority arole = roles.stream().findFirst().get();
		String role = arole.toString();
//		System.out.println("Role: "+role);
    	
        List<Product> product = productService.findAll();
        model.addAttribute("products", product);
        model.addAttribute("role", role);
        
        if(role.equalsIgnoreCase("ROLE_ADMIN"))
        	return "product/productList";
        else 
        	return "product/productListUser";
    }

    @RequestMapping(value = "/product/add", method = RequestMethod.GET)
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "product/addProduct";
    }

    @RequestMapping(value = "/product/add", method = RequestMethod.POST)
    public String addProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            List<User> users = userService.findAll();
            model.addAttribute("users", users);
            return "product/addProduct";
        }
        product = productService.add(product);
        return "redirect:/products";
    }

    @RequestMapping(value = "/product/delete/{id}", method = RequestMethod.GET)
    public String deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/products";
    }

    @RequestMapping(value = "/product/edit/{id}", method = RequestMethod.GET)
    public String updateProduct(@Valid @PathVariable Long id, Model model) {
        model.addAttribute("product",productService.get(id));
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "product/updateProduct";
    }
    /*@RequestMapping(value = "/product/search/{productNumber}", method = RequestMethod.GET)
    public String searchProduct(@Valid @PathVariable Long productNumber, Model model) {
        model.addAttribute("product",productService.get(productNumber));
        return "product/productList";
    }*/
}
