package cs544.project.service;

import cs544.project.model.Product;

import java.util.List;

public interface IProductService {
    public abstract List<Product> findAll();
    public abstract Product add(Product product);
    public abstract Product get(Long id);
    //public abstract void update(Long id);
    public abstract void delete(Long id);
}
