package com.java.good_received_note.repository;

import com.huston.springboot.crudgeneric.GenericCrudRepository;
import com.java.good_received_note.model.GoodReceivedNote;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodReceivedNoteRepository extends GenericCrudRepository<GoodReceivedNote, Integer> {
    GoodReceivedNote findById(int id);
//    List<GoodReceivedNote> findByPurchaseOrderId(int id);
//    List<GoodReceivedNote> findByPurchaseOrderIdAndPurchaseInvoiceId(int id, int purchaseInvoiceId);
}
