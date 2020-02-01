package com.auth.controller;

import com.auth.service.UploadService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    UploadService uploadService;

    @RequestMapping(path = "/uploadFile", method = RequestMethod.POST)
    JSONObject uploadFile(@RequestBody MultipartFile file) {
        return uploadService.uploadFile(file);
    }

    @RequestMapping(path = "/removeFile", method = RequestMethod.GET)
    String removeFile(@RequestParam String fileName) {
        return uploadService.removeFile(fileName);
    }

}
