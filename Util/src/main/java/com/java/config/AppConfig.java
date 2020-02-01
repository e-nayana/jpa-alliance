package com.java.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

@Configuration
public class AppConfig implements InitializingBean {
    public static String ACTIVE_PROFILE;
    public static String UPLOAD_BASE_DIR;
    public static String LOG_TABLE_NAME;
    public static String AZURE_CONNECTION_STRING;
    public static String FRONTEND_URL;
    public static String PUBLIC_CUSTOMER_ROLE_NAME;

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Value("${appConfig.uploadBaseDir}")
    private String uploadBaseDir;

//    @Value("${appConfig.log-table-name}")
    private String logTableName;

    @Value("${appConfig.frontend-url}")
    private String frontendUrl;

//    @Value("${azure.storage.connection-string}")
    private String azureConnectionString;

    @Value("${appConfig.role-config.public-customer-role-name}")
    private String publicCustomerRoleName;

    @Override
    public void afterPropertiesSet() {
        ACTIVE_PROFILE = activeProfile;
        UPLOAD_BASE_DIR = uploadBaseDir;
        LOG_TABLE_NAME = logTableName;
        AZURE_CONNECTION_STRING = azureConnectionString;
        FRONTEND_URL = frontendUrl;
        PUBLIC_CUSTOMER_ROLE_NAME = publicCustomerRoleName;
    }

    //    old methods
    private Properties accessActiveYml() throws IOException {
        Properties props = PropertiesLoaderUtils.loadAllProperties("application.yml");
        return PropertiesLoaderUtils.loadAllProperties("application-" + props.getProperty("active") + ".yml");
    }

    @Deprecated
    public String activeProfile(){
        try {
            Properties props = PropertiesLoaderUtils.loadAllProperties("application.yml");
            return props.getProperty("active");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "dev";
    }

    @Deprecated
    public String getUploadBaseDir() {
        String uploadBaseDir = null;
        try {
            Properties props = this.accessActiveYml();
            uploadBaseDir = props.getProperty("uploadBaseDir");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uploadBaseDir;
    }
}
