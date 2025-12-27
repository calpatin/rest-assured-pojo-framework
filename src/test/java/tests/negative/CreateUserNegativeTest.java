package tests.negative;

import base.BaseTest;
import clients.UsersClient;
import io.restassured.response.Response;
import org.testng.annotations.Test;


public class CreateUserNegativeTest extends BaseTest {

    private final UsersClient usersClient = new UsersClient();

    @Test
    public void createUser_onInvalidEndpoint_shouldReturn404() {
        Response response = usersClient.createUserOnInvalidEndpoint();
        response.then().statusCode(404);
    }
}
