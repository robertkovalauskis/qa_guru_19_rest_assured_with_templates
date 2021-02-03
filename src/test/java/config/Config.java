package config;

import org.aeonbits.owner.ConfigFactory;

public class Config {
    public static String baseUrl() {
        return config().baseURL();
    }

    public static String contentType() {
        return config().contentTYPE();
    }

    private static ApiConfig config() {
        return ConfigFactory.newInstance().create(ApiConfig.class);
    }
}
