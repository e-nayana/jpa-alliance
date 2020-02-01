package com.java.product.model;

import com.huston.springboot.crudgeneric.Alliance;
import com.huston.springboot.crudgeneric.GenericCrudEntity;
import com.huston.springboot.crudgeneric.WhereAlliance;
import com.java.brand.model.Brand;
import com.java.brand.repository.BrandRepository;
import com.java.category.model.Category;
import com.java.category.repository.CategoryRepository;
import com.java.inventory.model.StockCountView;
import com.java.inventory.repository.StockCountViewRepository;
import com.java.product.repository.ComboChildProductRepository;
import com.java.product.repository.ProductFeedbackRepository;
import com.java.product.repository.ProductImageRepository;
import com.java.product.repository.ProductVariationRepository;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Product extends GenericCrudEntity<Integer> {

    @NotNull(message = "Category cannot be null")
    private int categoryId;

    @NotNull(message = "Brand cannot be null")
    private int brandId;

    @NotNull(message = "Product Name cannot be null")
    private String productName;

    @NotNull(message = "Product Code cannot be null")
    private String productCode;

    private String uom;

    private String type;

    private double actualPrice;

    private double price;

    private double discount;

    private double shippingCost;

    private String description;

    private String warrantyStatus;

    private String stockStatus;

    private int rate;

    private int viewCount;

    private Boolean isFeatured = true;

    @Transient
    @Alliance(repositoryType = ProductImageRepository.class, foreignKey = "productId")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<ProductImage> productImages;

    @Transient
    @Alliance(repositoryType = ProductImageRepository.class, foreignKey = "productId")
    @WhereAlliance(filters = {"type=mainImage"}, allianceEntity = ProductImage.class)
    @NotFound(action = NotFoundAction.IGNORE)
    private List<ProductImage> mainProductImages;

    @Transient
    @Alliance(repositoryType = ProductVariationRepository.class, foreignKey = "productId", alliances = {"stockCountView"})
    @NotFound(action = NotFoundAction.IGNORE)
    private List<ProductVariation> productVariations;

    @Transient
    @Alliance(repositoryType = ProductFeedbackRepository.class, foreignKey = "productId")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<ProductFeedback> productFeedbacks;

    @Transient
    @Alliance(repositoryType = BrandRepository.class, localKey = "brandId", foreignKey = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Brand brand;

    @Transient
    @Alliance(repositoryType = CategoryRepository.class, localKey = "categoryId", foreignKey = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Category category;

    @Transient
    @Alliance(repositoryType = ComboChildProductRepository.class, foreignKey = "comboProductId", alliances = {"childProduct"})
    @NotFound(action = NotFoundAction.IGNORE)
    private List<ComboChildProduct> comboChildProducts;

    @Transient
    private List<ProductImage> toBeSavedProductImages;

    @Transient
    @Alliance(repositoryType = StockCountViewRepository.class, foreignKey = "productId")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<StockCountView> stockCountViewList;

    public Product() {
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public double getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(double actualPrice) {
        this.actualPrice = actualPrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWarrantyStatus() {
        return warrantyStatus;
    }

    public void setWarrantyStatus(String warrantyStatus) {
        this.warrantyStatus = warrantyStatus;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public Boolean getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(Boolean isFeatured) {
        this.isFeatured = isFeatured;
    }

    public List<ProductImage> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ProductImage> productImages) {
        this.productImages = productImages;
    }

    public List<ProductVariation> getProductVariations() {
        return productVariations;
    }

    public void setProductVariations(List<ProductVariation> productVariations) {
        this.productVariations = productVariations;
    }

    public List<ProductFeedback> getProductFeedbacks() {
        return productFeedbacks;
    }

    public void setProductFeedbacks(List<ProductFeedback> productFeedbacks) {
        this.productFeedbacks = productFeedbacks;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }

    public List<ProductImage> getToBeSavedProductImages() {
        return toBeSavedProductImages;
    }

    public void setToBeSavedProductImages(List<ProductImage> toBeSavedProductImages) {
        this.toBeSavedProductImages = toBeSavedProductImages;
    }

    public void setMainProductImages(List<ProductImage> mainProductImages) {
        this.mainProductImages = mainProductImages;
    }

    public List<ProductImage> getMainProductImages() {
        return mainProductImages;
    }

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }

    public List<ComboChildProduct> getComboChildProducts() {
    return comboChildProducts;
    }

    public void setComboChildProducts(List<ComboChildProduct> comboChildProducts) {
      this.comboChildProducts = comboChildProducts;
    }

    public List<StockCountView> getStockCountViewList() {
      return stockCountViewList;
    }

    public void setStockCountViewList(List<StockCountView> stockCountViewList) {
      this.stockCountViewList = stockCountViewList;
    }
}
