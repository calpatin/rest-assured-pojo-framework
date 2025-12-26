package clients;

import base.BaseApiClient;
import io.restassured.response.Response;
import models.response.UsersResponse;

import static io.restassured.RestAssured.given;

public class UsersClient extends BaseApiClient {

    private static final String USERS_ENDPOINT = "/users";
    private static final int DEFAULT_LIMIT = 30;

    public Response getAllUsers(){
        return request()
                .when()
                .get(USERS_ENDPOINT)
                .then()
                .extract()
                .response();
    }

    public Response getUsersById(int userId) {
        return request()
                .pathParam("id", userId)
                .when()
                .get(USERS_ENDPOINT + "/{id}")
                .then()
                .extract()
                .response();
    }

//    the endpoint does not support query param "page". The pagination is made based on "limit" and "skip", not on "page".
//    page --> skip/limit
    public Response getUsersByPage (int page) {

        int safePage = Math.max(page, 1);
        int skip = (safePage -1) * DEFAULT_LIMIT;

        return request()
                .queryParam("limit", DEFAULT_LIMIT)
                .queryParam("skip", skip)
                .when()
                .get(USERS_ENDPOINT)
                .then()
                .extract().response();
    }
}
