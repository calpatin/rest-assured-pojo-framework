package base;

import framework.config.ConfigManager;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public abstract class BaseTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = ConfigManager.getBaseUrl();

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
