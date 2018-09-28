package cs544.project.controller;

import cs544.project.model.Product;
import cs544.project.repository.IProductRepository;
import cs544.project.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RESTAPIController {

    @Autowired
    private IProductService productService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    @ResponseBody
    public List<Product> getProductsAPI(){
        return productService.findAll();
    }

    @GetMapping(value = "/products/{id}")
    @ResponseBody
    public Product getProductAPI(@PathVariable Long id){
        return productService.get(id);
    }
}
