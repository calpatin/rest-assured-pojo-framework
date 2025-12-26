package clients;

import io.restassured.response.Response;
import models.response.UsersResponse;

import static io.restassured.RestAssured.given;

public class UsersClient {

    private static final String USERS_ENDPOINT = "/users";

    public Response getUsersRawResponse(){
        return given()
                .when()
                .get(USERS_ENDPOINT);
    }

    public UsersResponse getUsers(){
        return getUsersRawResponse()
                .then()
                .statusCode(200)
                .extract()
                .as(UsersResponse.class);
    }
}
