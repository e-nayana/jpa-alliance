package com.java.category.controller;

import com.huston.rest.response.RESTResponse;
import com.huston.rest.response.RESTResponseManager;
import com.java.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("public/categories")
public class PublicCategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public RESTResponse index(){
        return RESTResponseManager.headerBuilder(HttpStatus.OK)
                .bodyBuilder()
                .setBodyAttribute("result",categoryService.activeList())
                .responseBuilder()
                .build();
    }
}
