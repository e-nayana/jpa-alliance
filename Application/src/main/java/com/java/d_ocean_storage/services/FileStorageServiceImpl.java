package com.java.d_ocean_storage.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.java.config.AppConfig;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;


/**
 * File managing service for images
 * if bucket name or folder dosn't exist in storage it will be created automatically
 * bucket name and folder name are same in digital ocean space
 */
@Service
public class FileStorageServiceImpl implements FileStorageService {

	private final String S3_BUCKET_NAME = "bucket_name";
	private final String FOLDER = "public/";
	private final String PUBLIC_ACCESSIBLE_URL = "https://imagespacedealbox.sgp1.digitaloceanspaces.com/bucket_name/public/";

	@Autowired
	AmazonS3 s3Client;

	@Override
	public String saveFile(MultipartFile multipartFile) throws IOException {
		String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
		String fileName = UUID.randomUUID().toString();

		String key = FOLDER + fileName + "." + extension;
		File fileToUpload = convertFromMultiPartToFile(multipartFile, fileName);

		s3Client.putObject(new PutObjectRequest(S3_BUCKET_NAME, key, fileToUpload)
				.withCannedAcl(CannedAccessControlList.PublicRead));

		removeConvertedFileFromLocalTemp(fileName);
		return PUBLIC_ACCESSIBLE_URL + fileName + "." + extension;
	}

	@Override
	public void deleteFile(String fileName) throws Exception {
		s3Client.deleteObject(new DeleteObjectRequest(S3_BUCKET_NAME, FOLDER + fileName));
	}

	/**
	 * save file in local storage in the server
	 * @param multipartFile
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	private File convertFromMultiPartToFile(MultipartFile multipartFile, String fileName) throws IOException {
		File file = new File(fileName);
		file.createNewFile();
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(multipartFile.getBytes());
		fos.close();
		return file;
	}

	/**
	 * delete the file which has been saved for converting
	 * @param fileName
	 * @return
	 */
	private boolean removeConvertedFileFromLocalTemp(String fileName){
		try {
			File myFile = new File(fileName);

			if (!myFile.isFile()) {
				return false;
			}
			myFile.delete();
			return true;


		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
