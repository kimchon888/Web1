package Package.name.com.example.coffeeshop.controllers;



import Package.name.com.example.coffeeshop.models.Product;
import Package.name.com.example.coffeeshop.services.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/")
public class ProductsController {

    @Autowired
    private ProductsRepository repo;

    // Trang chủ
    @GetMapping("/")
    public String homeProductList(Model model) {
        List<Product> products = repo.findAll();
        model.addAttribute("products", products);
        return "products/index";
    }

    // Trang Đồ ăn kèm
    @GetMapping("/SanPhamNoiBat")
    public String banchayProductList(Model model) {
        List<Product> products = repo.findAll();
        model.addAttribute("products", products);
        return "products/SanPhamNoiBat";
    }
    // Trang Đồ ăn kèm
    @GetMapping("/Doankem")
    public String doanKemProductList(Model model) {
        List<Product> products = repo.findAll();
        model.addAttribute("products", products);
        return "products/Doankem";
    }
    // Trang login
    @GetMapping("/login")
    public String loginProductList(Model model) {
        List<Product> products = repo.findAll();
        model.addAttribute("products", products);
        return "products/login";
    }

    // Trang lỗi
    @GetMapping("/custom-error")
    public String errorProductList(Model model) {
        List<Product> products = repo.findAll();
        model.addAttribute("products", products);
        return "products/error";
    }

    // Trang showcart
    @GetMapping("/showcart")
    public String showProductList(Model model) {
        List<Product> products = repo.findAll();
        model.addAttribute("products", products);
        return "products/showcart";
    }

    @GetMapping("/admin")
    public String adminProductList(Model model) {
        List<Product> products = repo.findByIsHiddenFalse();
        model.addAttribute("products", products);
        return "products/admin";
    }

    @GetMapping("/xemthem/{id}")
    public String showProductDetails(@PathVariable("id") Long productId, Model model) {
        Product product = repo.findById(productId).orElse(null);
        model.addAttribute("product", product);
        return "products/xemthem";
    }

    // Trang này khi người dùng click vào button thêm vào giỏ hàng
    @GetMapping("/cart/{id}")
    public String cartProductDetails(@PathVariable("id") Long productId, Model model) {
        Product product = repo.findById(productId).orElse(null);
        model.addAttribute("product", product);
        return "products/cart";
    }

}
