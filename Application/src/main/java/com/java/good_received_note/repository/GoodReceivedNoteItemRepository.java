package com.java.good_received_note.repository;

import com.huston.springboot.crudgeneric.GenericCrudRepository;
import com.java.good_received_note.model.GoodReceivedNoteItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodReceivedNoteItemRepository extends GenericCrudRepository<GoodReceivedNoteItem, Integer> {

//    @Query(value = "SELECT grni.product_id as product_id, SUM(grni.receiving_qty) as receiving_qty FROM good_received_note_item as grni " +
//            "JOIN good_received_note as grn on grn.id = grni.good_received_note_id " +
//            "WHERE grn.purchase_order_id = ?1 AND grn.is_active=1 GROUP BY grni.product_id ", nativeQuery = true)
//    List<Object[]> getReceivedProductIdsWithCount(int purchaseOrderId);
//
//    @Query(value = "SELECT grni.product_id as product_id, SUM(grni.receiving_qty) as receiving_qty FROM good_received_note_item as grni " +
//            "JOIN good_received_note as grn on grn.id = grni.good_received_note_id " +
//            "WHERE grn.purchase_order_id = ?1 AND grn.id != ?2 AND grn.is_active=1 GROUP BY grni.product_id ", nativeQuery = true)
//    List<Object[]> getReceivedProductIdsWithCountExceptCurrentGRN(int purchaseOrderId, int goodReceivedNoteId);


}
