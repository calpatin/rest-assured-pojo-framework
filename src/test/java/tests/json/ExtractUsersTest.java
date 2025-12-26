package tests.json;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class ExtractUsersTest extends BaseTest {

    @Test
    public void extractEmails_shouldReturnValidEmails() {
        Response response = given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        List<String> emails = jsonPath.getList("users.email");

        assertNotNull(emails, "Email list should not be null");
        assertFalse(emails.isEmpty(), "Email list should not be empty");

        for (String email : emails) {
            assertTrue(email.contains("@"), "Invalid email:" + email);
        }
    }
}
