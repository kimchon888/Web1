package Package.name.com.example.coffeeshop.repository;


import Package.name.com.example.coffeeshop.models.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;




public interface ProductsRepository extends JpaRepository<Product, Long> {
    List<Product> findByIsHiddenFalse();
}
