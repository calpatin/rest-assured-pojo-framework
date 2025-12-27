package tests.json;

import base.BaseTest;
import clients.UsersClient;
import io.restassured.response.Response;
import models.response.User;
import models.response.UsersResponse;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UsersPojoTest extends BaseTest {

    private final UsersClient usersClient = new UsersClient();

    @Test(groups = {"get", "happy"})
    public void usersResponse_shouldBeMappedCorrectly() {

        // Act
        Response response = usersClient.getAllUsers();
        UsersResponse usersResponse = response.as(UsersResponse.class);
        List<User> users = usersResponse.getUsers();

        // Assert
        assertThat(users)
                .isNotNull()
                .isNotEmpty();

        assertThat(users.get(0))
                .extracting(
                        User::getId,
                        User::getFirstName,
                        User::getLastName,
                        User::getEmail
                )
                .doesNotContainNull();
    }

    @Test
    public void usersResponse_shouldContainValidEmails() {

        // Act
        Response response = usersClient.getAllUsers();
        UsersResponse usersResponse = response.as(UsersResponse.class);

        // Assert
        assertThat(usersResponse.getUsers())
                .isNotEmpty()
                .isNotNull()
                .allMatch(user -> user.getEmail().contains("@"));
    }
}
