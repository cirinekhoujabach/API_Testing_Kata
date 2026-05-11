package com.booking.utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigFileReader {
    private static Properties properties = new Properties();

    static {
        try (InputStream is = ConfigFileReader.class.getClassLoader()
                .getResourceAsStream("config/config.properties")) {
            if (is == null) {
                throw new RuntimeException("Fichier config/config.properties introuvable dans resources");
            }
            properties.load(is);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors du chargement de la config", e);
        }
    }

    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        return (value != null) ? value.trim() : null;
    }
}