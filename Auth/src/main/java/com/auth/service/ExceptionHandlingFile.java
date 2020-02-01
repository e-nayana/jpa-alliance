package com.auth.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ExceptionHandlingFile {

    private static final String mainLocation = "Main/src/main/resources/exceptionLog/";
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    /*EXCEPTION HANDLING*/
    public static void createException(Throwable throwable){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object userID = authentication.getName();

        File file = new File(mainLocation+"log.txt");
        Date date  = new Date();

        PrintWriter write;

        String className  = throwable.getStackTrace()[0].getClassName();
        String methodName = throwable.getStackTrace()[0].getMethodName();
        int lineNumber    = throwable.getStackTrace()[0].getLineNumber();
        String error = throwable.getCause().getCause().getMessage();

        try{

            if (file.exists()){
                write = new PrintWriter(new FileOutputStream(file,true));

            }else{
                file.createNewFile();

                write = new PrintWriter(file);
            }

            write.println(dateFormat.format(date)+" "+"User : "+userID);
            write.println("Class "+className+" Method "+methodName+" Line Number "+lineNumber);
            write.println(throwable);
            write.println(error);
            write.println("");
            write.close();

        }catch(Exception err){
            System.out.println(err);
        }
    }
}
