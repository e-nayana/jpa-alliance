package com.java.service;

import net.minidev.json.JSONObject;
import org.springframework.data.domain.Page;

import java.util.HashMap;

public class ReturnResponse {

    public static HashMap<String, Object> response(Object object, int status) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("object", object);
        response.put("status", status);

        return response;
    }

    public static Pagenator.PagenatedObject paginationError(Exception err) {
        Page<Object> page = (Page<Object>) err;
        return Pagenator.setMetaData(page);
    }

    public static JSONObject returnSuccessJsonObject(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("statusText","success");
        return jsonObject;
    }

}
