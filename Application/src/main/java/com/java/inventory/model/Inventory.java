package com.java.inventory.model;

import com.huston.springboot.crudgeneric.Alliance;
import com.huston.springboot.crudgeneric.GenericCrudEntity;
import com.java.product.model.Product;
import com.java.product.model.ProductVariation;
import com.java.product.repository.ProductRepository;
import com.java.product.repository.ProductVariationRepository;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Inventory extends GenericCrudEntity<Integer> {

    private int inventoryTransferId;

    private int inventoryTransferItemId;

    private String date;

    private int productId;

    private int productVariationId = 0;

    private int parentId;

    private int goodReceivedNoteId = 0;

    private int customerOrderId = 0;

    private int goodReceivedNoteItemId = 0;

    private int customerOrderItemId = 0;

    private int salesInvoiceId = 0;

    private int salesInvoiceItemId = 0;

    private Integer returnTicketId = 0;
    private Integer returnTicketItemId = 0;

    private Integer rmaId = 0;
    private Integer rmaItemId = 0;

    private Integer costCenterId;

    private Integer warehouseId;

    private String uom;

    private double unitPrice;

    private double quantity;

    private String productType;

    private double subQuantity;

    @NotNull(message = "Type cannot be empty")
    @Enumerated(EnumType.STRING)
    private InventoryType type;

    private String serialNo;

    @Transient
    @Alliance(repositoryType = ProductRepository.class, localKey = "productId", foreignKey = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Product product;

    @Transient
    @Alliance(repositoryType = ProductVariationRepository.class, localKey = "productVariationId", foreignKey = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private ProductVariation productVariation;

    public int getInventoryTransferId() {
        return inventoryTransferId;
    }

    public void setInventoryTransferId(int inventoryTransferId) {
        this.inventoryTransferId = inventoryTransferId;
    }

    public int getInventoryTransferItemId() {
        return inventoryTransferItemId;
    }

    public void setInventoryTransferItemId(int inventoryTransferItemId) {
        this.inventoryTransferItemId = inventoryTransferItemId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getGoodReceivedNoteId() {
        return goodReceivedNoteId;
    }

    public void setGoodReceivedNoteId(int goodReceivedNoteId) {
        this.goodReceivedNoteId = goodReceivedNoteId;
    }

    public int getGoodReceivedNoteItemId() {
        return goodReceivedNoteItemId;
    }

    public void setGoodReceivedNoteItemId(int goodReceivedNoteItemId) {
        this.goodReceivedNoteItemId = goodReceivedNoteItemId;
    }

    public int getCustomerOrderId() {
        return customerOrderId;
    }

    public void setCustomerOrderId(int customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    public int getCustomerOrderItemId() {
        return customerOrderItemId;
    }

    public void setCustomerOrderItemId(int customerOrderItemId) {
        this.customerOrderItemId = customerOrderItemId;
    }

    public int getSalesInvoiceId() {
        return salesInvoiceId;
    }

    public void setSalesInvoiceId(int salesInvoiceId) {
        this.salesInvoiceId = salesInvoiceId;
    }

    public int getSalesInvoiceItemId() {
        return salesInvoiceItemId;
    }

    public void setSalesInvoiceItemId(int salesInvoiceItemId) {
        this.salesInvoiceItemId = salesInvoiceItemId;
    }

    public Integer getCostCenterId() {
        return costCenterId;
    }

    public void setCostCenterId(Integer costCenterId) {
        this.costCenterId = costCenterId;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public double getSubQuantity() {
        return subQuantity;
    }

    public void setSubQuantity(double subQuantity) {
        this.subQuantity = subQuantity;
    }

    public InventoryType getType() {
        return type;
    }

    public void setType(InventoryType type) {
        this.type = type;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getReturnTicketId() {
        return returnTicketId;
    }

    public void setReturnTicketId(Integer returnTicketId) {
        this.returnTicketId = returnTicketId;
    }

    public Integer getReturnTicketItemId() {
        return returnTicketItemId;
    }

    public void setReturnTicketItemId(Integer returnTicketItemId) {
        this.returnTicketItemId = returnTicketItemId;
    }

    public Integer getRmaId() {
        return rmaId;
    }

    public void setRmaId(Integer rmaId) {
        this.rmaId = rmaId;
    }

    public Integer getRmaItemId() {
        return rmaItemId;
    }

    public void setRmaItemId(Integer rmaItemId) {
        this.rmaItemId = rmaItemId;
    }

    public int getProductVariationId() {
        return productVariationId;
    }

    public void setProductVariationId(int productVariationId) {
        this.productVariationId = productVariationId;
    }

    public ProductVariation getProductVariation() {
        return productVariation;
    }

    public void setProductVariation(ProductVariation productVariation) {
        this.productVariation = productVariation;
    }
}
