package ru.qaway.bookstore.tests.props;

import lombok.SneakyThrows;

import java.io.InputStream;
import java.util.Properties;

public class Configuration {
    private static Properties prop;
    public static Properties getConfiguration(){
        return getConfiguration("config.properties");
    }
    @SneakyThrows
    public static Properties getConfiguration(String filename){
        try (InputStream inputStream = Configuration.class.getClassLoader().getResourceAsStream(filename)){
            if(inputStream == null){
                throw new IllegalArgumentException("Unable to find " + filename);
            }
            prop = new Properties();
            prop.load(inputStream);
        }
        return prop;
    }
}
