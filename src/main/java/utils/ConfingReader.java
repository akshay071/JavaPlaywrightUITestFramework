package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfingReader {
    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    private ConfingReader(){ }

    private static void loadProperties(){
        try(InputStream inputStream = ConfingReader.class.getClassLoader().getResourceAsStream("config.properties")){
            if(inputStream == null){
                throw new RuntimeException("config.properties file not found" + "in sec/test/resources");
            }
            PROPERTIES.load(inputStream);

        }catch(IOException exception){
            throw new RuntimeException("Unable to load config.properties", exception);

        }
    }

    public static String get(String key){
        String value = PROPERTIES.getProperty(key);
        if(value == null || value.isBlank()){
            throw new RuntimeException("Property is missing or blank: " + key);
        }
        return value.trim();
    }

    public static boolean getBoolean(String key){
        return Boolean.parseBoolean(get(key));
    }
}
