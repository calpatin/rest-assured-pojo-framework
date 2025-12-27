package tests.negative;

import base.BaseTest;
import clients.UsersClient;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UsersNegativeTest extends BaseTest {

    private final UsersClient usersClient = new UsersClient();

    @Test(groups = {"negative"})
    public void getUserByInvalidId_shouldReturn404() {

        Response response = usersClient.getUsersById(99999999);

        response.then().statusCode(404);
        assertThat(response.jsonPath().getString("message"),
                anyOf(containsString("not found"),
                        containsString("User not found")
                ));

    }

    @Test(groups = {"negative"})
    public void getUsersWithNegativePage_shouldFallbackToDefaultPage() {
        // Act
        Response response = usersClient.getUsersByPage(-1);

        // Assert
        response.then().statusCode(200);

        assertThat(response.jsonPath().getList("users"), not(empty()));
        assertThat(response.jsonPath().getInt("skip"), is(0));
    }

    @Test(groups = {"negative"})
    public void getUsers_withVeryLargePage_shouldReturnEmptyUsersList() {
        UsersClient client = new UsersClient();

        Response response = client.getUsersByPage(999999);

        response.then().statusCode(200);

        var users = response.jsonPath().getList("users");
        assertThat(users, empty());

        // these should be in the DummyJSON /users response
        Integer total = response.jsonPath().getInt("total");
        Integer skip = response.jsonPath().getInt("skip");
        Integer limit = response.jsonPath().getInt("limit");

        org.testng.Assert.assertNotNull(total);
        org.testng.Assert.assertNotNull(skip);
        org.testng.Assert.assertNotNull(limit);
    }


}
