package com.auth.service;

import com.java.config.AppConfig;
import net.bytebuddy.utility.RandomString;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
public class UploadService {

    public static JSONObject uploadFile(MultipartFile file) {

        String mainLocation = new AppConfig().getUploadBaseDir();

        InputStream inputStream = null;
        OutputStream outputStream = null;

        String fileName = new RandomString(10).nextString();
        fileName = fileName + file.getOriginalFilename();

        long fileSize = file.getSize();

        File filePath = new File(mainLocation);
        if (!filePath.exists()){
            filePath.mkdirs();
        }

        File newFile = new File(mainLocation + fileName);

        JSONObject response = new JSONObject();

        try {
            inputStream = file.getInputStream();

            if (!newFile.exists()) {
                newFile.createNewFile();
            }
            outputStream = new FileOutputStream(newFile);
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            outputStream.close();

            response.put("filename", fileName);
            response.put("size", fileSize);

        } catch (IOException e) {
            response.put("error", e.toString());
        }

        return response;

    }

    public static String renameFile(String fileName, String newFileName) {
        try {
            File myFile = new File(AppConfig.UPLOAD_BASE_DIR + fileName);
            if (!myFile.isFile()) {
                return "File not found";
            }
            myFile.renameTo(new File(AppConfig.UPLOAD_BASE_DIR + "/" + newFileName));
        } catch (Exception e) {
            return e.toString();
        }
        return "done";
    }

    public static String moveFile(String fileName, String newfolderName) {

        try {
            File myFile = new File(AppConfig.UPLOAD_BASE_DIR + fileName);

            if (!myFile.isFile()) {
                return "File not found";
            }

            File filePath = new File(AppConfig.UPLOAD_BASE_DIR + newfolderName);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            myFile.renameTo(new File(AppConfig.UPLOAD_BASE_DIR + newfolderName + "/" + fileName));


        } catch (Exception e) {
            return e.toString();
        }

        return "done";

    }

    public static String removeFile(String fileName) {

        try {
            File myFile = new File(AppConfig.UPLOAD_BASE_DIR + fileName);

            if (!myFile.isFile()) {
                return "File not found";
            }

            myFile.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "done";

    }

    public static File getFile(String fileName) throws FileNotFoundException {
        File myFile = null;
        myFile = new File(AppConfig.UPLOAD_BASE_DIR + fileName);

        if(!myFile.isFile()){
            throw new FileNotFoundException("File not found");
        }
        return myFile;

    }
}
