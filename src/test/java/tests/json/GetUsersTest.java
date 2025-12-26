package tests.json;

import org.testng.annotations.Test;
import base.BaseTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetUsersTest extends BaseTest {

    @Test
    public void getUsers_shouldReturnValidUsersList() {
        given()
                .queryParam("page", 2)
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .body("users", not(empty()))
                .body("users[0].id", greaterThan(0))
                .body("users[0].email", matchesRegex(".*@*"))
                .body("total", greaterThan(0));
    }
}
