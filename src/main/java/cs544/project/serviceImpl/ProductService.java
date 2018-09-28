package cs544.project.serviceImpl;

import cs544.project.model.Product;
import cs544.project.repository.IProductRepository;
import cs544.project.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public Product add(Product product) {
        return productRepository.save(product);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public Product get(Long id) {
        Optional<Product> opt = productRepository.findById(id);
        return opt.orElse(null);
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
