package Package.name.com.example.coffeeshop.services;

import Package.name.com.example.coffeeshop.models.Product;
import Package.name.com.example.coffeeshop.repository.ProductsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductsRepository productRepository;

    public ProductService(ProductsRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
