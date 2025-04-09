package com.swaglabs.utils;


import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class jsonUtils {
    private final static String Json_File_Path = "src/test/resources/";
    String jsonReader;
    String jsonFileName;
    public jsonUtils(String jsonFileName) {
        this.jsonFileName = jsonFileName ;
        try {
            JSONObject data = (JSONObject) new JSONParser().parse(new FileReader(Json_File_Path + jsonFileName+".json"));
            jsonReader= data.toJSONString();
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
        }

    }
    public String getJsonData(String jsonPath){
        String testData = "";
        try{
            testData = JsonPath.read(jsonReader,jsonPath);
        } catch (Exception e) {
            LogsUtils.error(e.getMessage() , "no result found for json path : "+ jsonPath + "in the json file : "+ jsonFileName);
        }
        return testData;
    }
}
