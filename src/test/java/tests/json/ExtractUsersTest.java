package tests.json;

import base.BaseTest;
import clients.UsersClient;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class ExtractUsersTest extends BaseTest {

    @Test(groups = {"get", "happy"})
    public void extractEmails_shouldReturnValidEmails() {
//    Arrange
        UsersClient usersClient = new UsersClient();
//    Act
        Response response = usersClient.getAllUsers();
        response.then().statusCode(200);

        JsonPath jsonPath = response.jsonPath();
        List<String> emails = jsonPath.getList("users.email");

//        Assert
        assertNotNull(emails, "Email ist should not be null");
        assertFalse(emails.isEmpty(), "Email list should not be empty");

        for (String email : emails) {
            assertTrue(email.contains("@"), "Invalid email: " + email);
        }

    }
}
