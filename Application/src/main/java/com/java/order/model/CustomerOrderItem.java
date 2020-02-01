package com.java.order.model;

import com.huston.springboot.crudgeneric.Alliance;
import com.huston.springboot.crudgeneric.GenericCrudEntity;
import com.java.brand.model.Brand;
import com.java.brand.repository.BrandRepository;
import com.java.order.repository.OrderItemRepository;
import com.java.product.model.Product;
import com.java.product.model.ProductVariation;
import com.java.product.repository.ProductRepository;
import com.java.product.repository.ProductVariationRepository;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.EAN;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Entity
@Table(name = "customer_order_item")
public class CustomerOrderItem extends GenericCrudEntity<Integer> {

    @Column(name = "customer_order_id") 
    private Integer customerOrderId;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "product_variation_id")
    private Integer productVariationId;

    @Column(name = "self_parent_id")
    private Integer selfParentId;

    @Column(name = "unit_price")
    private Double unitPrice;

    @Column(name = "current_product")
    private String currentProduct;

    private Integer quantity;
    private Double discount;
    private Double total;

    @Transient
    @Alliance(repositoryType = ProductRepository.class, localKey = "productId", foreignKey = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Product product;

    @Transient
    @Alliance(repositoryType = ProductVariationRepository.class, localKey = "productVariationId", foreignKey = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private ProductVariation productVariation;


    @Transient
    @Alliance(repositoryType = OrderItemRepository.class, foreignKey = "selfParentId", alliances = {"product","productVariation"})
    @NotFound(action = NotFoundAction.IGNORE)
    private List<CustomerOrderItem> childCustomerOrderItems;

    private String type;

    public Integer getCustomerOrderId() {
        return customerOrderId;
    }

    public void setCustomerOrderId(Integer customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductVariationId() {
        return productVariationId;
    }

    public void setProductVariationId(Integer productVariationId) {
        this.productVariationId = productVariationId;
    }

    public Integer getSelfParentId() {
        return selfParentId;
    }

    public void setSelfParentId(Integer selfParentId) {
        this.selfParentId = selfParentId;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCurrentProduct() {
        return currentProduct;
    }

    public void setCurrentProduct(String currentProduct) {
        this.currentProduct = currentProduct;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductVariation getProductVariation() {
        return productVariation;
    }

    public void setProductVariation(ProductVariation productVariation) {
        this.productVariation = productVariation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<CustomerOrderItem> getChildCustomerOrderItems() {
        return childCustomerOrderItems;
    }

    public void setChildCustomerOrderItems(List<CustomerOrderItem> childCustomerOrderItems) {
        this.childCustomerOrderItems = childCustomerOrderItems;
    }

    @Override
    public String toString() {
        return "CustomerOrderItem{" +
                "customerOrderId=" + customerOrderId +
                ", productId=" + productId +
                ", productVariationId=" + productVariationId +
                ", selfParentId=" + selfParentId +
                ", unitPrice=" + unitPrice +
                ", currentProduct='" + currentProduct + '\'' +
                ", quantity=" + quantity +
                ", discount=" + discount +
                ", total=" + total +
                ", product=" + product +
                ", productVariation=" + productVariation +
                ", type='" + type + '\'' +
                ", childCustomerOrderItems=" + childCustomerOrderItems +
                '}';
    }
}
