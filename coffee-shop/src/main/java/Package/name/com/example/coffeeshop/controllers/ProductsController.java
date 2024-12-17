package Package.name.com.example.coffeeshop.controllers;



import Package.name.com.example.coffeeshop.models.Product;
import Package.name.com.example.coffeeshop.models.ProductDTO;
import Package.name.com.example.coffeeshop.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
    // Thêm sản phẩm mới
    @PostMapping("/add-product")
    public String addProduct(
            @ModelAttribute ProductDTO productDTO,
            RedirectAttributes redirectAttributes) {
        try {
            // Tạo đối tượng Product từ ProductDTO
            Product product = new Product();
            product.setName(productDTO.getName());
            product.setPricesgoc(productDTO.getPricesgoc());
            product.setPrices(productDTO.getPrices());
            product.setDanhgia(productDTO.getDanhgia());
            product.setDescription(productDTO.getDescription());
            product.setBaiviet(productDTO.getBaiviet());

            // Xử lý file `image`
            if (!productDTO.getImage().isEmpty()) {
                String uploadDir = "src/main/resources/static/images/imageProducts/";
                String fileName = productDTO.getImage().getOriginalFilename();
                productDTO.getImage().transferTo(new File(uploadDir + fileName));
                product.setImage(fileName);
            }

            // Xử lý file `img_detail`
            if (!productDTO.getImg_detail().isEmpty()) {
                String uploadDir = "src/main/resources/static/images/imageDetails/";
                String fileName = productDTO.getImg_detail().getOriginalFilename();
                productDTO.getImg_detail().transferTo(new File(uploadDir + fileName));
                product.setImg_detail(fileName);
            }

            // Lưu vào cơ sở dữ liệu
            repo.save(product);
            redirectAttributes.addFlashAttribute("success", "Thêm sản phẩm thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
        }
        return "redirect:/";
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



