package com.java.good_received_note.service;

import com.huston.springboot.crudgeneric.GenericCrudServiceImpl;
import com.huston.springboot.crudgeneric.exception.AllianceException;
import com.huston.springboot.crudgeneric.exception.CrudGenericException;
import com.huston.springboot.crudgeneric.exception.CustomCheckException;
import com.java.good_received_note.model.GoodReceivedNote;
import com.java.good_received_note.model.GoodReceivedNoteItem;
import com.java.good_received_note.repository.GoodReceivedNoteItemRepository;
import com.java.good_received_note.repository.GoodReceivedNoteRepository;
import com.java.inventory.model.Inventory;
import com.java.inventory.model.InventoryType;
import com.java.inventory.service.InventoryService;
import com.java.service.ExcelGenerator;
import com.java.service.LoggerFile;
import com.java.service.Pagenator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class GoodReceivedNoteServiceImpl extends GenericCrudServiceImpl<GoodReceivedNoteRepository, GoodReceivedNote> implements GoodReceivedNoteService{

    private LoggerFile loggerFile = new LoggerFile(this.getClass());
    private GoodReceivedNoteItemRepository goodReceivedNoteItemRepository;
    private InventoryService inventoryService;

    @Autowired
    GoodReceivedNoteServiceImpl(GoodReceivedNoteItemRepository goodReceivedNoteItemRepository,
                                InventoryService inventoryService){
        this.goodReceivedNoteItemRepository = goodReceivedNoteItemRepository;
        this.inventoryService = inventoryService;
    }

    @Override
    public Pagenator.PagenatedObject list(int page, int perPage, HashMap<String,Object> filters){
        return list(page,perPage,searchCriteria(filters));
    }

    private Specification<GoodReceivedNote> searchCriteria(HashMap<String,Object> filters) {
        return new Specification<GoodReceivedNote>() {
            @Override
            public Predicate toPredicate(Root<GoodReceivedNote> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                Integer goodReceivedNoteId = (Integer) filters.putIfAbsent("goodReceivedNoteId", 0);
                String dateStr = (String) filters.putIfAbsent("date", null);
                ArrayList<Integer> goodReceivedNoteIds = (ArrayList<Integer>) filters.putIfAbsent("goodReceivedNoteIds", new ArrayList<Integer>());

                if (goodReceivedNoteId != null && goodReceivedNoteId != 0) {
                    predicates.add(criteriaBuilder.equal(root.get("id"), goodReceivedNoteId));
                }
                if (dateStr != null) {
                    predicates.add(criteriaBuilder.like(root.get("date"), dateStr));
                }

                if (goodReceivedNoteIds!= null && goodReceivedNoteIds.size() > 0) {
                    predicates.add(root.get("id").in(goodReceivedNoteIds));
                }

                criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        };
    }

    @Override
    public GoodReceivedNote show(Integer id) throws CrudGenericException {
        GoodReceivedNote goodReceivedNote = null;
        try {
            goodReceivedNote = super.show(id, "items");
        } catch (AllianceException e) {
            goodReceivedNote = super.show(id);
        }

        return goodReceivedNote;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public GoodReceivedNote update(@Valid GoodReceivedNote goodReceivedNote) throws CrudGenericException {
        GoodReceivedNote existingEntity = entityUpdateValidator(goodReceivedNote);
        goodReceivedNote = save(goodReceivedNote);
        if(!existingEntity.getIsActive()){
            throw new CustomCheckException("GRN deactivated");
        }
        loggerFile.createLog("update",LoggerFile.LogType.INFO,"update success - " + goodReceivedNote.getId().toString() + " - old data",existingEntity);
        loggerFile.createLog("update",LoggerFile.LogType.INFO,"update success - " + goodReceivedNote.getId().toString() + "- new data",goodReceivedNote);
        return goodReceivedNote;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public GoodReceivedNote save(GoodReceivedNote goodReceivedNote) throws CrudGenericException {

        double total = 0.00;
        double subTotal = 0.00;
        double totalQty = 0.00;

        for (GoodReceivedNoteItem item : goodReceivedNote.getItems()) {
            double amount = item.getUnitPrice() * item.getReceivingQuantity();
            subTotal = subTotal + amount;
            totalQty = totalQty + item.getReceivingQuantity();
            total = total + amount;
            item.setAmount(amount);
        }

        goodReceivedNote.setTotalReceivingQuantity(totalQty);
        goodReceivedNote.setTotal(total);

        GoodReceivedNote savedGoodReceivedNote = repository.save(goodReceivedNote);

        if (goodReceivedNote.getItems() != null) {

            for (GoodReceivedNoteItem goodReceivedNoteItem : goodReceivedNote.getItems()) {

                goodReceivedNoteItem.setGoodReceivedNoteId(savedGoodReceivedNote.getId());
                GoodReceivedNoteItem savedGoodReceivedNoteItem = goodReceivedNoteItemRepository.save(goodReceivedNoteItem);

                Inventory existInventory = inventoryService.showInventoryByGoodReceiveNoteItem(goodReceivedNoteItem.getId());

                if (existInventory != null) {
                    inventoryService.deactivateInventory(existInventory.getId());
                }

                Inventory inventory = new Inventory();
                inventory.setDate(goodReceivedNote.getDate());
                inventory.setGoodReceivedNoteId(savedGoodReceivedNote.getId());
                inventory.setGoodReceivedNoteItemId(savedGoodReceivedNoteItem.getId());
                inventory.setProductVariationId(savedGoodReceivedNoteItem.getProductVariationId());
                inventory.setProductId(savedGoodReceivedNoteItem.getProductId());
                inventory.setQuantity(goodReceivedNoteItem.getReceivingQuantity());
                inventory.setUnitPrice(savedGoodReceivedNoteItem.getUnitPrice());
                inventory.setUom(savedGoodReceivedNoteItem.getUom());
                inventory.setType(InventoryType.PURCHASE);
                inventoryService.inventoryManagement(inventory);
            }
        }

        return savedGoodReceivedNote;

    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void remove(Integer id) throws CrudGenericException{
        GoodReceivedNote goodReceivedNote = show(id);
        goodReceivedNote.setIsActive(false);
        loggerFile.createLog("remove",LoggerFile.LogType.INFO,"disabled row started - " + goodReceivedNote.toString());

        if (goodReceivedNote.getItems() != null) {
            for (GoodReceivedNoteItem goodReceivedNoteItem : goodReceivedNote.getItems()) {
                if (goodReceivedNote.getIsActive()) {
                    continue;
                }
                int goodReceivedNoteId = goodReceivedNote.getId();
                List<Inventory> checkForInventoryItems = inventoryService.get(new Specification<Inventory>() {
                    @Override
                    public Predicate toPredicate(Root<Inventory> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                        List<Predicate> pd = new ArrayList<>();
                        pd.add(criteriaBuilder.equal(root.get("goodReceivedNoteId"), goodReceivedNoteId));
                        return criteriaBuilder.and(pd.toArray(new Predicate[0]));
                    }
                });
                if(checkForInventoryItems != null && checkForInventoryItems.size()>0){
                    for(Inventory inventory:checkForInventoryItems){
                        inventoryService.delete(inventory.getId());
                    }
                }
            }
        }
        goodReceivedNote = repository.save(goodReceivedNote);
        loggerFile.createLog("remove",LoggerFile.LogType.INFO,"disabled row ended - " + goodReceivedNote.toString(), goodReceivedNote);

    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void activate(Integer id) throws CrudGenericException{
        GoodReceivedNote goodReceivedNote = show(id);
        goodReceivedNote.setIsActive(true);
        loggerFile.createLog("activate",LoggerFile.LogType.INFO,"activate row started - " + goodReceivedNote.toString());

        goodReceivedNote = repository.save(goodReceivedNote);
        loggerFile.createLog("remove",LoggerFile.LogType.INFO,"activate row ended - " + goodReceivedNote.toString(), goodReceivedNote);

    }

    public byte[] excelDownload(int page, int perPage, HashMap<String,Object> filters) throws IOException {

        Pagenator.PagenatedObject ordersObj = list(page,perPage,searchCriteria(filters),"items");
        List<GoodReceivedNote> orders = (List<GoodReceivedNote>) ordersObj.getResult();

        String columnHeaders[] = {"Note ID","Date","Reference","Total Receiving Quantity","Total","Status","Items","Note"};

        ArrayList orderArray = new ArrayList();
        for (int i = 0; i < orders.size(); i++) {

            GoodReceivedNote order = orders.get(i);

            Object[] rows = new Object[12];
            rows[0] = order.getId();
            rows[2] = order.getDate();
            rows[4] = order.getReference();
            rows[7] = order.getTotalReceivingQuantity();
            rows[8] = order.getTotal();
            rows[9] = order.getIsActive()?"Active":"Deleted";

            String itemString = "";
            for (int j = 0; j < order.getItems().size(); j++) {
                GoodReceivedNoteItem goodReceivedNoteItem = order.getItems().get(j);
                itemString += goodReceivedNoteItem.getProduct().getProductCode() + " - " + goodReceivedNoteItem.getProduct().getProductName() + " \r\n";
            }
            rows[10] = itemString;
            rows[11] = order.getNote();
            orderArray.add(rows);
        }
        loggerFile.createLog("excelDownload", LoggerFile.LogType.INFO,"GRN list Excel Download - filters ",filters);
        return new ExcelGenerator(columnHeaders, orderArray).getExcelFileByteArray();

    }

}

