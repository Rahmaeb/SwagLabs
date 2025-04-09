package com.swaglabs.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

public class propertiesUtils {
    private final static  String propertiesPath = "src/main/resources";

    private propertiesUtils(){
        super();
    }
    public static Properties loadProperties() {
        try{
        Properties properties = new Properties();
        Collection<File> propertiesFilesList;
        propertiesFilesList = FileUtils.listFiles(new File(propertiesPath), new String[]{"properties"}, true);
        propertiesFilesList.forEach(propertyFile -> {
            try {
                properties.load(new FileInputStream(propertyFile));
            } catch (IOException e) {
                LogsUtils.error(e.getMessage());
            }
            properties.putAll(System.getProperties());
            System.getProperties().putAll(properties);
        });
        LogsUtils.info("Loading properties file data ");
        return properties;
    }
    catch (Exception e){
            LogsUtils.error("Failed to load Properties File data because: " ,e.getMessage()  );
            return  null;
    }

    }
    public static String getPropertiesValue (String key){
        try{
            return System.getProperty(key);

        } catch (Exception e) {
            LogsUtils.error("failed to get value " + e.getMessage());
            return  "" ;
        }
    }

}
