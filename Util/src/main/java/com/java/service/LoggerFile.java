package com.java.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.module.AuthUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class LoggerFile {

    private Class clazz;
    private Logger logger;

    public LoggerFile(Class aClazz){
        clazz = aClazz;
        logger = LoggerFactory.getLogger(aClazz);
    }

    public void createLog(String methodName , LogType type, String message, Object logData){
        String logDataString = null;
        try {
            logDataString = new ObjectMapper().writeValueAsString(logData);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        this.createLog(methodName, type, message, logDataString);
    }

    public void createLog(String methodName , LogType type, String message){
        this.createLog(methodName, type, message, null);
    }

    public void createLog(String methodName, LogType type, String message, String logData){

        try {
            this.createLog(this.clazz, methodName, type, message, logData);
//            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//            Date date = new Date();
//
//            String uniqueId = System.currentTimeMillis() + new RandomString(6).nextString().toUpperCase();
//
//            Log log = new Log(uniqueId,uniqueId);
//            log.setClassName(clazz.getName());
//            log.setLogData(logData);
//            log.setLogType(type.toString());
//            log.setMessage(message);
//            log.setMethodName(methodName);
//            log.setTime(dateFormat.format(date));
//            try {
//                log.setAuthUserId(AuthUserService.authUserId().toString());
//                log.setAuthUserEmail(AuthUserService.authUserEmail());
//            }catch (Exception e){
//                log.setAuthUserId("Unauthorised User");
//                log.setAuthUserEmail("Unauthorised User");
//            }
//
//            if("prod".equals(AppConfig.ACTIVE_PROFILE)){
//                AzureStorageTable azureStorageTable = new AzureStorageTable();
//                azureStorageTable.createEntity(AppConfig.LOG_TABLE_NAME,log);
//            }
//
//            if("error".equals(type)){
//                insertErrorToLogFile(methodName, message, logData);
//            }

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public void insertErrorToLogFile(String methodName, String message, String logData){

        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();

            logger.error("##########################################  ERROR  START ########################################################################");
            logger.error(dateFormat.format(date));
            logger.error(methodName);
            logger.error(message);
            logger.error(logData);
            try {
                logger.error("user - " + AuthUserService.authUserId() + " - " + AuthUserService.authUserEmail());
            }catch (Exception e){
                logger.error("Unauthorised User");
            }
            logger.error("##########################################  ERROR  END ##########################################################################");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void createLog(Class t, String methodName , LogType type , String message, String logData){
        Logger logger = LoggerFactory.getLogger(t);
        try{
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();

            if (type == LogType.INFO){
                logger.info("##########################################  INFORMATION  START ########################################################################");
                logger.info(dateFormat.format(date));
                logger.info(methodName);
                logger.info(message);
                logger.info(logData);
                try {
                    logger.info("user - " + AuthUserService.authUserId() + " - " + AuthUserService.authUserEmail());
                }catch (Exception e){
                    logger.info("Unauthorised User");
                }
                logger.info("##########################################  INFORMATION  END ##########################################################################");
            }else if (type == LogType.WARN){
                logger.warn("##########################################  Warning  START ########################################################################");
                logger.warn(dateFormat.format(date));
                logger.warn(methodName);
                logger.warn(message);
                logger.warn(logData);
                try {
                    logger.warn("user - " + AuthUserService.authUserId() + " - " + AuthUserService.authUserEmail());
                }catch (Exception e){
                    logger.warn("Unauthorised User");
                }
                logger.warn("##########################################  Warning  END ##########################################################################");
            }else if (type == LogType.ERROR){
                logger.error("##########################################  ERROR  START ########################################################################");
                logger.error(dateFormat.format(date));
                logger.error(methodName);
                logger.error(message);
                logger.error(logData);
                try {
                    logger.error("user - " + AuthUserService.authUserId() + " - " + AuthUserService.authUserEmail());
                }catch (Exception e){
                    logger.error("Unauthorised User");
                }
                logger.error("##########################################  ERROR  END ##########################################################################");
            }
        }catch (Exception err){
            System.out.print(err);
        }
    }

    @Deprecated
    public static HashMap<String, Object> createLogDetailsFromException(Exception e){
        HashMap<String, Object> errorUpdateTheRecored = new HashMap<>();
        errorUpdateTheRecored.put("object", e);
        errorUpdateTheRecored.put("error message", e.getMessage());
        errorUpdateTheRecored.put("error cause", e.getCause());
        errorUpdateTheRecored.put("line number", e.getStackTrace()[0].getLineNumber());
        errorUpdateTheRecored.put("method", e.getStackTrace()[0].getMethodName());
        errorUpdateTheRecored.put("file", e.getStackTrace()[0].getFileName());
        return errorUpdateTheRecored;
    }

    @Deprecated
    public LoggerFile(){}

    @Deprecated
    public void createLog(Class clazz,String methodName , String type, String message, Object logData){
        this.clazz = clazz;
        this.createLog(methodName, LogType.getValue(type), message, logData);
    }

    @Deprecated
    public void createLog(Class clazz,String methodName , String type, String message, String logData){
        this.clazz = clazz;
        this.createLog(methodName, LogType.getValue(type), message, logData);
    }

    public enum LogType{
        INFO,
        ERROR,
        DEBUG,
        WARN;

        public static LogType getValue(String value){
            if(value.equalsIgnoreCase(INFO.toString()))
                return LogType.INFO;
            else if(value.equalsIgnoreCase(ERROR.toString()))
                return LogType.ERROR;
            else if(value.equalsIgnoreCase(DEBUG.toString()))
                return LogType.DEBUG;
            else if(value.equalsIgnoreCase(WARN.toString()))
                return LogType.WARN;
            else
                return null;
        }

    }
}
