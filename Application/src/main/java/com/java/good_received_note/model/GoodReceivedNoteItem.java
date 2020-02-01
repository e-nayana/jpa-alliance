package com.java.good_received_note.model;

import com.huston.springboot.crudgeneric.Alliance;
import com.huston.springboot.crudgeneric.GenericCrudEntity;
import com.huston.springboot.crudgeneric.WhereAlliance;
import com.java.inventory.model.Inventory;
import com.java.inventory.repository.InventoryRepository;
import com.java.product.model.Product;
import com.java.product.model.ProductVariation;
import com.java.product.repository.ProductRepository;
import com.java.product.repository.ProductVariationRepository;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Entity
public class GoodReceivedNoteItem extends GenericCrudEntity<Integer> {

    private int goodReceivedNoteId;

    private int productId;

    private int productVariationId = 0;

    private String description;

    private String uom;

    private double unitPrice;

    private double receivingQuantity;

    private double amount;

    @Transient
    @Alliance(repositoryType = ProductRepository.class, localKey = "productId", foreignKey = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Product product;

    @Transient
    @Alliance(repositoryType = ProductVariationRepository.class, localKey = "productVariationId", foreignKey = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private ProductVariation productVariation;

    @Transient
    @Alliance(repositoryType = InventoryRepository.class, foreignKey = "goodReceivedNoteItemId")
    @WhereAlliance(filters = {"isActive=true"}, allianceEntity = Inventory.class)
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Inventory> inventory;

    @Transient
    @Alliance(repositoryType = InventoryRepository.class, foreignKey = "goodReceivedNoteItemId")
    @WhereAlliance(filters = {"isActive=true", "serialNo IS NOT NULL"}, allianceEntity = Inventory.class)
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Inventory> inventorySerials;

    @Transient
    private double requestedQuantity;

    @Transient
    private double preReceivedQuantity;

    public GoodReceivedNoteItem() {
    }

    public int getGoodReceivedNoteId() {
        return goodReceivedNoteId;
    }

    public void setGoodReceivedNoteId(int goodReceivedNoteId) {
        this.goodReceivedNoteId = goodReceivedNoteId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public double getRequestedQuantity() {
        return requestedQuantity;
    }

    public void setRequestedQuantity(double requestedQuantity) {
        this.requestedQuantity = requestedQuantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getReceivingQuantity() {
        return receivingQuantity;
    }

    public void setReceivingQuantity(double receivingQuantity) {
        this.receivingQuantity = receivingQuantity;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getProductVariationId() {
        return productVariationId;
    }

    public void setProductVariationId(int productVariationId) {
        this.productVariationId = productVariationId;
    }

    public List<Inventory> getInventory() {
        return inventory;
    }

    public void setInventory(List<Inventory> inventory) {
        this.inventory = inventory;
    }

    public List<Inventory> getInventorySerials() {
        return inventorySerials;
    }

    public void setInventorySerials(List<Inventory> inventorySerials) {
        this.inventorySerials = inventorySerials;
    }

    public double getPreReceivedQuantity() {
        return preReceivedQuantity;
    }

    public void setPreReceivedQuantity(double preReceivedQuantity) {
        this.preReceivedQuantity = preReceivedQuantity;
    }

    public ProductVariation getProductVariation() {
        return productVariation;
    }

    public void setProductVariation(ProductVariation productVariation) {
        this.productVariation = productVariation;
    }
}
