package com.java.product.model;

import com.huston.springboot.crudgeneric.Alliance;
import com.huston.springboot.crudgeneric.GenericCrudEntity;
import com.java.inventory.model.StockCountView;
import com.java.inventory.repository.StockCountViewRepository;
import com.java.product.repository.ProductRepository;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Entity
@Table(name="combo_child_product")
public class ComboChildProduct extends GenericCrudEntity<Integer> {

  @Column(name = "combo_product_id")
  private int comboProductId;

  @Column(name = "child_product_id")
  private int childProductId;

  @Column(name = "price")
  private Double price;

  @Transient
  @Alliance(repositoryType = ProductRepository.class, foreignKey = "id" , localKey = "comboProductId")
  @NotFound(action = NotFoundAction.IGNORE)
  private Product comboProduct;

  @Transient
  @Alliance(repositoryType = ProductRepository.class, foreignKey = "id" , localKey = "childProductId", alliances = {"productVariations"})
  @NotFound(action = NotFoundAction.IGNORE)
  private Product childProduct;

  @Transient
  @Alliance(repositoryType = StockCountViewRepository.class, foreignKey = "productId" , localKey = "childProductId")
  @NotFound(action = NotFoundAction.IGNORE)
  private List<StockCountView> stockCountViewList;

  public int getComboProductId() {
    return comboProductId;
  }

  public void setComboProductId(int comboProductId) {
    this.comboProductId = comboProductId;
  }

  public int getChildProductId() {
    return childProductId;
  }

  public void setChildProductId(int childProductId) {
    this.childProductId = childProductId;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Product getComboProduct() {
    return comboProduct;
  }

  public void setComboProduct(Product comboProduct) {
    this.comboProduct = comboProduct;
  }

  public Product getChildProduct() {
    return childProduct;
  }

  public void setChildProduct(Product childProduct) {
    this.childProduct = childProduct;
  }

  public List<StockCountView> getStockCountViewList() {
    return stockCountViewList;
  }

  public void setStockCountViewList(List<StockCountView> stockCountViewList) {
    this.stockCountViewList = stockCountViewList;
  }
}
