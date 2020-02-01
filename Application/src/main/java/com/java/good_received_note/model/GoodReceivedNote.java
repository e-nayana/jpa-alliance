package com.java.good_received_note.model;

import com.huston.springboot.crudgeneric.Alliance;
import com.huston.springboot.crudgeneric.GenericCrudEntity;
import com.java.good_received_note.repository.GoodReceivedNoteItemRepository;
import com.sun.istack.NotNull;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Entity
public class GoodReceivedNote extends GenericCrudEntity<Integer> {

    private String reference;
    private String note;
    private String date;

    private double totalReceivingQuantity;

    private double total;

    @Transient
    @Alliance(repositoryType = GoodReceivedNoteItemRepository.class, foreignKey = "goodReceivedNoteId", alliances = {"product","inventorySerials", "productVariation"})
    @NotFound(action = NotFoundAction.IGNORE)
    private List<GoodReceivedNoteItem> items;

    public GoodReceivedNote() {
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotalReceivingQuantity() {
        return totalReceivingQuantity;
    }

    public void setTotalReceivingQuantity(double totalReceivingQuantity) {
        this.totalReceivingQuantity = totalReceivingQuantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<GoodReceivedNoteItem> getItems() {
        return items;
    }

    public void setItems(List<GoodReceivedNoteItem> items) {
        this.items = items;
    }

}
