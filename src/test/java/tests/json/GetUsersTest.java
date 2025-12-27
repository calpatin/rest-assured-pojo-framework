package tests.json;

import base.BaseTest;
import clients.UsersClient;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class GetUsersTest extends BaseTest {

    @Test(groups = {"get", "happy"})
    public void getUsers_shouldReturnValidUsersList() {

        UsersClient usersClient = new UsersClient();

        usersClient.getUsersByPage(2)
                .then()
                .statusCode(200)
                .body("users", not(empty()))
                .body("users[0].id", greaterThan(0))
                .body("users[0].email", matchesRegex(".*@.*"))
                .body("total", greaterThan(0));
    }
}
