package com.toolshop.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toolshop.utils.LoggerUtils;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class JsonDataReader {

    private static final Logger logger = LoggerUtils.getLogger(JsonDataReader.class);
    private static final String FILE_PATH = "src/test/resources/testData/toolShopData.json";
    private static JsonNode jsonData;

    static {
        loadJson();
    }

    private static void loadJson() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            jsonData = objectMapper.readTree(new File(FILE_PATH));
        } catch (IOException error) {
            logger.error("Failed to load JSON file {}", error.getMessage());
            throw new RuntimeException("Failed to load JSON file: " + FILE_PATH, error);
        }
    }

    public static String getData(String category, String key) {
        try {
            return jsonData.path(category).path(key).asText();
        } catch (Exception error) {
            logger.error("Failed to read JSON file {}", error.getMessage());
            throw new RuntimeException("Failed to read JSON file: " + FILE_PATH, error);
        }
    }
}
