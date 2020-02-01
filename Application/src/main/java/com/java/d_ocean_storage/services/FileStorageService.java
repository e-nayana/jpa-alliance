package com.java.d_ocean_storage.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorageService {

	/**
	 * save a file in digital ocean storage which will be accessible publicly
	 * @param multipartFile
	 * @throws IOException
	 */
	String saveFile(MultipartFile multipartFile) throws IOException;

	/**
	 * deleting file
	 * @param fileName
	 * @throws Exception
	 */
	void deleteFile(String fileName) throws Exception;
}
