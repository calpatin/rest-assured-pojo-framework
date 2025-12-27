package tests.post;

import base.BaseTest;
import clients.UsersClient;
import io.restassured.response.Response;
import models.request.CreateUserRequest;
import models.response.CreateUserResponse;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CreateUserHappyFlowTest extends BaseTest {

    private final UsersClient usersClient = new UsersClient();

    @Test(groups = {"post", "happy"})
    public void createUser_shouldReturnCreatedUser() {

        CreateUserRequest userRequest = new CreateUserRequest("Catalin",
                "Cretu",
                48,
                "calpatin@gmail.com");

        CreateUserResponse response = usersClient.createUser(userRequest).as(CreateUserResponse.class);

        // Assert
        assertThat(response.getId(), greaterThan(0));
        assertThat(response.getFirstName(), is("Catalin"));
        assertThat(response.getLastName(), is("Cretu"));
        assertThat(response.getEmail(), is("calpatin@gmail.com"));
        assertThat(response.getAge(), is(48));
        assertThat(response.getRole(), is("user"));



    }
}
