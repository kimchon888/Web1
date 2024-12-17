package Package.name.com.example.coffeeshop.api;


import Package.name.com.example.coffeeshop.models.Product;
import Package.name.com.example.coffeeshop.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Đánh dấu lớp này là một RestController, cho phép xử lý các yêu cầu HTTP
@RestController
@RequestMapping("/api/products") // Định nghĩa đường dẫn cơ sở cho các endpoint
public class ProductApiController {

    @Autowired // Tự động tiêm ProductsRepository vào controller
    private ProductsRepository productRepository;

    // Endpoint để lấy tất cả sản phẩm
    @GetMapping("/getall")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Endpoint để lấy sản phẩm theo ID
    @GetMapping("/getbyid/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        // Tìm sản phẩm theo ID và trả về ResponseEntity
        return productRepository.findById((long) id)
                .map(ResponseEntity::ok) // Nếu tìm thấy, trả về sản phẩm với mã 200
                .orElse(ResponseEntity.notFound().build()); // Nếu không tìm thấy, trả về mã 404
    }

    // Endpoint để cập nhật sản phẩm theo ID
    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product) {
        // Kiểm tra xem sản phẩm có tồn tại không
        if (!productRepository.existsById((long) id)) {
            return ResponseEntity.notFound().build(); // Nếu không tồn tại, trả về mã 404
        }
        product.setId(id); // Thiết lập ID cho sản phẩm
        Product updatedProduct = productRepository.save(product); // Lưu sản phẩm đã cập nhật
        return ResponseEntity.ok(updatedProduct); // Trả về sản phẩm đã cập nhật với mã 200
    }

    // Endpoint để xóa sản phẩm theo ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        // Kiểm tra xem sản phẩm có tồn tại không
        if (!productRepository.existsById((long) id)) {
            return ResponseEntity.notFound().build(); // Nếu không tồn tại, trả về mã 404
        }
            productRepository.deleteById((long) id); // Xóa sản phẩm theo ID
        return ResponseEntity.noContent().build(); // Trả về mã 204 (No Content) sau khi xóa
    }
    // Endpoint để thêm sản phẩm mới
    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        // Lưu sản phẩm mới vào cơ sở dữ liệu
        Product savedProduct = productRepository.save(product); // Lưu sản phẩm
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct); // Trả về sản phẩm đã lưu với mã 201
    }

}