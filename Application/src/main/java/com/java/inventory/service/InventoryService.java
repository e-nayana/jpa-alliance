package com.java.inventory.service;

import com.huston.springboot.crudgeneric.GenericCrudService;
import com.java.inventory.model.Inventory;

import java.util.List;

public interface InventoryService extends GenericCrudService<Inventory> {

    /**
     * get current inventory summary
     */
    List getInventorySummary();

    /**
     * get current inventory summary
     */
    List inventorySummaryForEbayStockUpdate();

    /**
     * save inventory
     */
    Inventory inventoryManagement(Inventory inventory);

    boolean deactivateInventory(int id);
    Inventory showInventoryBySalesInvoiceItem(int salesInvoiceItemId, String serialNo);

    Inventory showInventoryByGoodReceiveNoteItem(int goodReceiveNoteItemId);
    Inventory showInventoryByCustomerOrderItem(int customerOrderItemId);

}
