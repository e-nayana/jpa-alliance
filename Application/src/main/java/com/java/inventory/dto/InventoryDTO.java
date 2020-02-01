package com.java.inventory.dto;

public class InventoryDTO {

    private int id;
    private String date;
    private int productId;
    private int parentId;
    private int goodReceivedNoteId;
    private int deliveryOrderId;
    private int goodReceivedNoteItemId;
    private int deliveryOrderItemId;
    private Integer costCenterId;
    private Integer warehouseId;
    private String uom;
    private double unitPrice;
    private double quantity;
    private String productType;
    private double subQuantity;
    private String type;
    private String serialNo;
    private boolean isActive;
//    private ProductDTO product;
//    private GoodReceivedNote goodReceivedNote;
    private int salesInvoiceId;
    private int salesInvoiceItemId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getDeliveryOrderId() {
        return deliveryOrderId;
    }

    public void setDeliveryOrderId(int deliveryOrderId) {
        this.deliveryOrderId = deliveryOrderId;
    }

    public int getGoodReceivedNoteItemId() {
        return goodReceivedNoteItemId;
    }

    public void setGoodReceivedNoteItemId(int goodReceivedNoteItemId) {
        this.goodReceivedNoteItemId = goodReceivedNoteItemId;
    }

    public int getDeliveryOrderItemId() {
        return deliveryOrderItemId;
    }

    public void setDeliveryOrderItemId(int deliveryOrderItemId) {
        this.deliveryOrderItemId = deliveryOrderItemId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSerialNo() {
        return serialNo;
    }

//    public ProductDTO getProduct() {
//        return product;
//    }
//
//    public void setProduct(ProductDTO product) {
//        this.product = product;
//    }
//
//    public GoodReceivedNote getGoodReceivedNote() {
//        return goodReceivedNote;
//    }
//
//    public void setGoodReceivedNote(GoodReceivedNote goodReceivedNote) {
//        this.goodReceivedNote = goodReceivedNote;
//    }

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

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }


}
