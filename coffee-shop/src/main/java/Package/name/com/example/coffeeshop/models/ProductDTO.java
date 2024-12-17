package Package.name.com.example.coffeeshop.models;

import org.springframework.web.multipart.MultipartFile;

public class ProductDTO {
    private String name;
    private double pricesgoc;
    private double prices;
    private String danhgia;
    private String description;
    private String baiviet;
    private MultipartFile image; // Lưu file upload
    private MultipartFile img_detail; // Lưu file upload

    // Getter và Setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPricesgoc() {
        return pricesgoc;
    }

    public void setPricesgoc(double pricesgoc) {
        this.pricesgoc = pricesgoc;
    }

    public double getPrices() {
        return prices;
    }

    public void setPrices(double prices) {
        this.prices = prices;
    }

    public String getDanhgia() {
        return danhgia;
    }

    public void setDanhgia(String danhgia) {
        this.danhgia = danhgia;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBaiviet() {
        return baiviet;
    }

    public void setBaiviet(String baiviet) {
        this.baiviet = baiviet;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public MultipartFile getImg_detail() {
        return img_detail;
    }

    public void setImg_detail(MultipartFile img_detail) {
        this.img_detail = img_detail;
    }
}
