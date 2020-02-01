package com.java.brand.controller;

import com.huston.springboot.crudgeneric.exception.AllianceException;
import com.huston.springboot.crudgeneric.exception.CrudGenericException;
import com.java.brand.model.Brand;
import com.java.brand.service.BrandService;
import com.java.dto.PaginatedListRequest;
import com.java.product.model.Product;
import com.java.service.Pagenator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "brands")
public class BrandController {

    @Autowired
    BrandService brandService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> index(){
        return new ResponseEntity<>(brandService.activeList(), HttpStatus.OK);
    }

    /**
     * get paginated record list
     * @return Pagenator.PagenatedObject
     */
    @RequestMapping(path = "list", method = RequestMethod.POST)
    public ResponseEntity<?> list(@RequestBody PaginatedListRequest request){
        Pagenator.PagenatedObject page = brandService.list(request.getPage(),request.getPerPage(), request.getFilter());
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    /**
     * create new record
     * @param brand
     * @throws CrudGenericException
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody @Valid Brand brand) throws CrudGenericException, AllianceException {
        return new ResponseEntity<>(brandService.create(brand), HttpStatus.OK);
    }

    @RequestMapping(path = "update", method = RequestMethod.POST)
    public ResponseEntity<?> update(@RequestBody @Valid Brand brand) throws CrudGenericException {
        return new ResponseEntity<>(brandService.update(brand), HttpStatus.OK);
    }

    @RequestMapping(path = "show", method = RequestMethod.GET)
    public ResponseEntity<?> show(@RequestParam Integer id) throws CrudGenericException {
        return new ResponseEntity<>(brandService.show(id), HttpStatus.OK);
    }

    /**
     * no auth needed for public
     * @Return brand list
     */
    @RequestMapping(path = "/no-auth", method = RequestMethod.GET)
    public ResponseEntity<?> noAuthIndex(){
        return new ResponseEntity<>(brandService.activeList(), HttpStatus.OK);
    }


//    @RequestMapping(path = "/get_brand_list", method = RequestMethod.GET)
//    public ResponseEntity<?> getBrandList(){
//        try {
//            new LoggerFile().createLog(this.getClass(), "getBrandList", "info", "get brand list", null);
//            return new ResponseEntity<Object>(brandService.getActiveBrandList(), HttpStatus.OK);
//        } catch (Exception err) {
//            new LoggerFile().createLog(this.getClass(), "getBrandList", "error", "get brand list", err);
//            return new ResponseEntity<Object>(err, HttpStatus.CONFLICT);
//        }
//    }
}
