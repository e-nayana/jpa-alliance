package com.java.d_ocean_storage.controllers;

import com.huston.rest.response.RESTResponse;
import com.huston.rest.response.RESTResponseManager;
import com.java.d_ocean_storage.services.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(path = "image")
public class DOceanImageFileController {

	FileStorageService fileStorageService;

	@Autowired
	public DOceanImageFileController(FileStorageService fileStorageService) {
		this.fileStorageService = fileStorageService;
	}

	@RequestMapping(method = RequestMethod.POST)
	public RESTResponse saveImage(@RequestParam(value = "file", required = true) MultipartFile file) throws IOException {
		return RESTResponseManager.headerBuilder(HttpStatus.OK)
				.bodyBuilder()
				.setBodyAttribute("url", fileStorageService.saveFile(file))
				.responseBuilder()
				.build();
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void deleteById(@PathVariable("fileName") String fileName) throws Exception {
		fileStorageService.deleteFile(fileName);
	}

}
