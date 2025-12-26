package tests.json;

import base.BaseTest;
import clients.UsersClient;
import models.response.User;
import models.response.UsersResponse;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UsersPojoTest  extends BaseTest {

    @Test
    public void usersResponse_shouldBeMappedCorrectly() {
        // Arrange
        UsersClient usersClient = new UsersClient();

        // Act
        UsersResponse usersResponse = usersClient.getUsers();
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
        // Arrange
        UsersClient usersClient = new UsersClient();

        // Act
        UsersResponse usersResponse = usersClient.getUsers();

        // Assert
        assertThat(usersResponse.getUsers())
                .allMatch(user -> user.getEmail().contains("@"));
    }
}
