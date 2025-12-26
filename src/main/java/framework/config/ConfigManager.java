package framework.config;

public class ConfigManager {
    private static final String BASE_URL = "https://dummyjson.com";

    //prevenim instantierea
    private ConfigManager() {

    }

    public static String getBaseUrl() {
        return BASE_URL;
    }
}
