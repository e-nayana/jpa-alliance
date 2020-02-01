package com.java.inventory.repository;

import com.huston.springboot.crudgeneric.GenericCrudRepository;
import com.java.inventory.model.Inventory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends GenericCrudRepository<Inventory,Integer> {

    Inventory findById(int Id);

    @Query(value = "SELECT p.id, p.item_code, p.item_name, sum(i.quantity) as quantity, p.uom, p.online_item_id FROM inventory i JOIN " +
            "product p ON i.product_id = p.id WHERE i.is_active = 1 GROUP BY i.product_id", nativeQuery = true)
    List<Object[]> inventorySummary();

    @Query(value = "SELECT invsum.id, invsum.item_code, invsum.item_name, invsum.quantity as quantity, invsum.uom, poi.online_item_id, poi.api_type, poi.id as product_online_item_table_id " +
            "FROM (SELECT p.id, p.item_code, p.item_name, sum(i.quantity) as quantity, p.uom, p.online_item_id FROM inventory i JOIN " +
            "product p ON i.product_id = p.id WHERE i.is_active = 1 GROUP BY i.product_id) as invsum " +
            "JOIN product_online_item poi ON poi.product_id = invsum.id ", nativeQuery = true)
    List<Object[]> inventorySummaryForEbayStockUpdate();

    Inventory findFirstBySalesInvoiceItemIdAndSerialNoAndIsActive(int goodReceivedNoteItemId , String serialNo, boolean isActive);
    Inventory findFirstByGoodReceivedNoteItemIdAndIsActive(int goodReceivedNoteItemId, boolean isActive);
    Inventory findFirstByCustomerOrderItemIdAndIsActive(int customerOrderItemId, boolean isActive);

}
