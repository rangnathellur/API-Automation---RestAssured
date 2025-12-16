package com.ranganath.framework.config;

import java.io.InputStream;
import java.util.Properties;

/**
 * Centralized configuration reader.
 * Loads config.properties once and provides read-only access
 * across the automation framework.
 */
public final class ConfigReader {

    private static final Properties properties = new Properties();

    // Static block ensures configuration is loaded only once
    static {
        try (InputStream inputStream =
                     ConfigReader.class
                             .getClassLoader()
                             .getResourceAsStream("config.properties")) {

            if (inputStream == null) {
                throw new RuntimeException(
                        "config.properties file not found in src/test/resources");
            }

            properties.load(inputStream);

        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to load configuration properties", e);
        }
    }

    // Private constructor prevents instantiation
    private ConfigReader() {
    }

    /**
     * Returns the value for a given key from config.properties
     */
    public static String get(String key) {
        return properties.getProperty(key);
    }

    /**
     * Returns the value for a key or throws an error if missing.
     * Useful for mandatory configs like base.url
     */
    public static String getRequired(String key) {
        String value = properties.getProperty(key);
        if (value == null || value.isEmpty()) {
            throw new RuntimeException("Missing required configuration: " + key);
        }
        return value;
    }
}
