package com.java.good_received_note.service;


import com.huston.springboot.crudgeneric.GenericCrudService;
import com.huston.springboot.crudgeneric.exception.CrudGenericException;
import com.java.good_received_note.model.GoodReceivedNote;

import java.io.IOException;
import java.util.HashMap;

public interface GoodReceivedNoteService extends GenericCrudService<GoodReceivedNote> {

    /**
     * show method override
     */
    GoodReceivedNote show(Integer id) throws CrudGenericException;

    /**
     * Download GRN Excel List
     */
    byte[] excelDownload(int page, int perPage, HashMap<String,Object> filters) throws IOException;

    /**
     * remove method override
     */
    void remove(Integer id) throws CrudGenericException;

    /**
     * activate good rec note override
     */
    void activate(Integer id) throws CrudGenericException;

}
