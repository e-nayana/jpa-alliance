package com.java.inventory.model;

public enum InventoryType {
    TRANSFER_IN("TRANSFER_IN"),
    TRANSFER_OUT("TRANSFER_OUT"),
    PURCHASE("PURCHASE"),
    SALE("SALE"),
    CREDIT_NOTE_IN("CREDIT_NOTE_IN"),
    RMA_OUT("RMA_OUT");

    public final String label;

    InventoryType(String label) {
        this.label = label;
    }

    public static String toString(InventoryType inventoryType){
        return inventoryType.toString();
    }
}
