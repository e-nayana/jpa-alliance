package com.java.good_received_note.controller;

import com.huston.springboot.crudgeneric.exception.CrudGenericException;
import com.java.dto.PaginatedListRequest;
import com.java.good_received_note.model.GoodReceivedNote;
import com.java.good_received_note.service.GoodReceivedNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/good-received-notes")
public class GoodReceivedNoteController {

    @Autowired
    GoodReceivedNoteService goodReceivedNoteService;

    /**
     * get paginated record list
     * @return Pagenator.PagenatedObject
     */
    @RequestMapping(path = "list", method = RequestMethod.POST)
    public ResponseEntity<?> list(@RequestBody PaginatedListRequest request){
        return new ResponseEntity<>(goodReceivedNoteService.list(request.getPage(),request.getPerPage(), request.getFilter()), HttpStatus.OK);
    }

    /**
     * show record
     * @return goodReceivedNote
     */
    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public ResponseEntity<?> show(@PathVariable Integer id) throws CrudGenericException {
        return new ResponseEntity<>(goodReceivedNoteService.show(id), HttpStatus.OK);
    }

    /**
     * create new record
     * @param goodReceivedNote
     * @throws CrudGenericException
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody @Valid GoodReceivedNote goodReceivedNote) throws CrudGenericException {
        try{
            return new ResponseEntity<>(goodReceivedNoteService.create(goodReceivedNote), HttpStatus.OK);
        }catch (DataIntegrityViolationException err) {
            return new ResponseEntity<Object>(err.getCause().getCause(), HttpStatus.CONFLICT);
        }
    }

    /**
     * update existing record
     * @param goodReceivedNote and id
     * @return goodReceivedNote
     * @throws CrudGenericException
     */
    @RequestMapping(path = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody @Valid GoodReceivedNote goodReceivedNote) throws CrudGenericException {
        try{
            return new ResponseEntity<>(goodReceivedNoteService.update(goodReceivedNote), HttpStatus.OK);
        }catch (DataIntegrityViolationException err) {
            return new ResponseEntity<Object>(err.getCause().getCause(), HttpStatus.CONFLICT);
        }
    }

    /**
     * remove the record
     * @param id
     * @throws CrudGenericException
     */
    @RequestMapping(value = "{id}/remove", method = RequestMethod.PUT)
    public ResponseEntity<?> remove(@PathVariable Integer id) throws CrudGenericException{
        goodReceivedNoteService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * activate the record
     * @param id
     * @throws CrudGenericException
     */
    @RequestMapping(value = "{id}/activate", method = RequestMethod.PUT)
    public ResponseEntity<?> activate(@PathVariable Integer id) throws CrudGenericException{
        goodReceivedNoteService.activate(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * download excel
     * @Hashmap -> invoiceId and template
     */
    @RequestMapping(path = "/excel-download", method = RequestMethod.POST,produces = "application/octet-stream")
    public ResponseEntity excelDownload(@RequestBody final PaginatedListRequest request) throws IOException {
        return new ResponseEntity<byte[]>(goodReceivedNoteService.excelDownload(request.getPage(),request.getPerPage(), request.getFilter()), HttpStatus.OK);
    }

}
