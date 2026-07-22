package com.saucedemo.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saucedemo.config.ConfigReader;
import java.io.File;

public class JsonReader {

    private static JsonNode root;

    public static JsonNode getNode(String path){
        if(root == null){
            try {
                root = new ObjectMapper().readTree(new File(ConfigReader.get("test.data.file")));
            }catch(Exception e){
                throw new RuntimeException("data file is not loaded: " + e.getMessage());
            }
        }
        JsonNode node = root;
        for(String key : path.split("\\.")){
            node = node.get(key);
        }
        return node;
    }

    public static String get(String path){
        return getNode(path).asText();
    }
}
