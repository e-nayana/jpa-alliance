package com.java.product.model;

import com.huston.springboot.crudgeneric.Alliance;
import com.huston.springboot.crudgeneric.GenericCrudEntity;
import com.java.inventory.model.StockCountView;
import com.java.inventory.repository.StockCountViewRepository;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class ProductVariation extends GenericCrudEntity<Integer> {

    @NotNull(message = "Product cannot be null")
    private int productId;

    private String color;
    private String size;
    private double value;

    @Transient
    @Alliance(repositoryType = StockCountViewRepository.class, foreignKey = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private StockCountView stockCountView;

    public ProductVariation() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public StockCountView getStockCountView() {
      return stockCountView;
    }

    public void setStockCountView(StockCountView stockCountView) {
        this.stockCountView = stockCountView;
      }
}
