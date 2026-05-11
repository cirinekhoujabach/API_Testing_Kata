package com.booking.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private static Properties properties;

    static {
        try {
            properties = new Properties();
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            System.err.println("ERREUR : Impossible de charger le fichier config.properties");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * Récupère une valeur du fichier properties à partir de sa clé
     * @param key La clé (ex: base.url)
     * @return La valeur nettoyée des espaces
     */
    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value != null) {
            return value.trim(); // Enlève les espaces ou retours à la ligne parasites
        }
        throw new RuntimeException("La clé '" + key + "' n'a pas été trouvée dans config.properties");
    }
}