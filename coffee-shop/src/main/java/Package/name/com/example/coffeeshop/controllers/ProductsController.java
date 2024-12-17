package Package.name.com.example.coffeeshop.controllers;



import Package.name.com.example.coffeeshop.models.Product;
import Package.name.com.example.coffeeshop.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/")
public class ProductsController {

    @Autowired
    private ProductsRepository repo;

//     Trang chủ
    @GetMapping("/")
    public String homeProductList(Model model) {
        List<Product> products = repo.findAll();
        model.addAttribute("products", products);
        return "products/index";
    }

//    @GetMapping("/")
//    public String home(Model model) {
//        // Lấy danh sách sản phẩm từ cơ sở dữ liệu
//        List<Product> products = repo.findAll();
//        model.addAttribute("products", products);
//
//        // Kiểm tra xem có sản phẩm mới thêm không
//        if (model.containsAttribute("newProduct")) {
//            model.addAttribute("newProduct", model.getAttribute("newProduct"));
//        }
//
//        return "products/index"; // Trả về trang home
//    }


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
    @PostMapping("/")
    public String addProduct(@ModelAttribute Product product,
                             @RequestParam("image") MultipartFile image,
                             RedirectAttributes redirectAttributes) {
        try {
            // Kiểm tra thông tin sản phẩm
            if (product.getName() == null || product.getPrices() <= 0 || product.getPricesgoc() <= 0) {
                redirectAttributes.addFlashAttribute("error", "Tên sản phẩm, giá bán và giá gốc không được để trống!");
                return "redirect:/admin"; // Quay lại trang admin
            }

            // Xử lý hình ảnh
            if (image != null && !image.isEmpty()) {
                String imagePath = "C:/path/to/your/images/" + image.getOriginalFilename();
                image.transferTo(new File(imagePath)); // Lưu tệp hình ảnh
                product.setImage(image.getOriginalFilename()); // Lưu tên tệp vào trường image
            } else {
                product.setImage(null); // Hoặc xử lý trường hợp không có hình ảnh
            }

            // Lưu sản phẩm
            repo.save(product);
            redirectAttributes.addFlashAttribute("success", "Thêm sản phẩm thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
        }
        return "redirect:/"; // Quay lại trang home
    }


    // Xóa sản phẩm
    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Xóa sản phẩm thành công!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Sản phẩm không tồn tại!");
        }
        return "redirect:/admin";
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
