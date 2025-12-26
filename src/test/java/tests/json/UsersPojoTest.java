package tests.json;

import models.response.User;
import models.response.UsersResponse;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

public class UsersPojoTest extends BaseTest {

    @Test
    public void usersResponse_shouldMapCorrectlyToPojo() {

        UsersResponse usersResponse =
                given()
                        .when()
                        .get("/users")
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(UsersResponse.class);
        assertThat(usersResponse.getUsers(), not(empty()));
        assertThat(usersResponse.getTotal(), greaterThan(0));

//        Validate first user
        User firstUser = usersResponse.getUsers().get(0);

        assertThat(firstUser.getEmail(), containsString("@"));
        assertThat(firstUser.getAddress().getCity(), notNullValue());
        assertThat(firstUser.getCompany().getName(), not(isEmptyOrNullString()));
        assertThat(firstUser.getBank().getCardType(), notNullValue());
        assertThat(firstUser.getCrypto().getCoin(), notNullValue());
    }

    @Test
    public void allUsers_shouldHaveValidFields() {
        UsersResponse usersResponse = given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract()
                .as(UsersResponse.class);

        for (User user : usersResponse.getUsers()) {

            assertNotNull(user.getId(), "User Id should not be null");
            assertTrue(user.getId() > 0, "User Id should be positive");

            assertNotNull(user.getEmail(), "User emal should not be null");
            assertTrue(user.getEmail().contains("@"), "User email should contain @");

            assertNotNull(user.getFirstName(), "User firstName should not be null");
            assertFalse(user.getFirstName().isEmpty(), "User firstName should not be empty");

            assertNotNull(user.getLastName(), "User firstName should not be null");
            assertFalse(user.getLastName().isEmpty(), "User firstName should not be empty");
        }
    }
}
