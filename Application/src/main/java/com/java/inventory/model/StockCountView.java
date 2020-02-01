package com.java.inventory.model;

import com.huston.springboot.crudgeneric.GenericCrudEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "stock_count_view")
public class StockCountView extends GenericCrudEntity<Integer> {

  @Column(name = "product_id")
  int productId;

  @Column(name = "product_code")
  String productCode;

  @Column(name = "product_name")
  String productName;

  @Column(name = "uom")
  String uom;

  @Column(name = "quantity")
  Double quantity;

  @Column(name = "variation_color")
  String variationColor;

  @Column(name = "variation_size")
  String variationSize;

  @Column(name = "product_variation_id")
  int productVariationId;

  public String getProductCode() {
    return productCode;
  }

  public void setProductCode(String productCode) {
    this.productCode = productCode;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getUom() {
    return uom;
  }

  public void setUom(String uom) {
    this.uom = uom;
  }

  public Double getQuantity() {
    return quantity;
  }

  public void setQuantity(Double quantity) {
    this.quantity = quantity;
  }

  public String getVariationColor() {
    return variationColor;
  }

  public void setVariationColor(String variationColor) {
    this.variationColor = variationColor;
  }

  public String getVariationSize() {
    return variationSize;
  }

  public void setVariationSize(String variationSize) {
    this.variationSize = variationSize;
  }

  public int getProductId() {
    return productId;
  }

  public void setProductId(int productId) {
    this.productId = productId;
  }

  public int getProductVariationId() {
    return productVariationId;
  }

  public void setProductVariationId(int productVariationId) {
    this.productVariationId = productVariationId;
  }
}
