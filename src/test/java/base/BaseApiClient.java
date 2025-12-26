package base;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseApiClient {

    protected RequestSpecification request(){
        return given()
                .baseUri(RestAssured.baseURI)
                .contentType("applicatin/json");
    }
}
