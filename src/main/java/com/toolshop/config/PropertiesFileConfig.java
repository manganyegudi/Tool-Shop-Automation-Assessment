package com.toolshop.config;

import com.toolshop.utils.LoggerUtils;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileConfig {

    private static final Logger logger = LoggerUtils.getLogger(PropertiesFileConfig.class);
    private static final Properties properties = new Properties();
    private static final String FILE_PATH = "src/main/resources/config.properties";

    static {
        loadProperties();
    }

    private static void loadProperties() {
        try (FileInputStream inputStream = new FileInputStream(FILE_PATH)) {
            properties.load(inputStream);
        } catch (IOException error) {
            logger.error("Failed to load properties from {} with error message: {}", FILE_PATH, error.getMessage());
            throw new RuntimeException("Failed to load properties", error);
        }
    }

    public static String getProperty(String key) {
        String value = properties.getProperty(key);

        if (value == null || value.isEmpty()) {
            logger.warn("Property '{}' is empty or missing", key);
            throw new IllegalArgumentException("Property '" + key + "' is empty or missing");
        }

        return value;
    }

    public static void reloadProperties() {
        loadProperties();
        logger.info("Properties file reloaded successfully.");
    }
}
