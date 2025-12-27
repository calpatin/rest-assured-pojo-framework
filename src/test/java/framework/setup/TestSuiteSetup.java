package framework.setup;

import framework.config.ConfigManager;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

public class TestSuiteSetup {

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        RestAssured.baseURI = ConfigManager.getBaseUrl();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        System.out.println("=== API Test Suite Initialized ===");
        System.out.println("Base URI: " + RestAssured.baseURI);
    }
}
