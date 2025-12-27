package tests.json;

import base.BaseTest;
import clients.UsersClient;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class NestedUsersTest extends BaseTest {

    private final UsersClient usersClient = new UsersClient();

    @Test(groups = {"get", "happy"})
    public void nestedFields_shouldBePresentAndValid() {
        Response response = usersClient.getAllUsers();
        response.then().statusCode(200);

        JsonPath jsonPath = response.jsonPath();

//        cities
        List<String> cities = jsonPath.getList("users.address.city");
        assertThat(cities, not(empty()));
        assertThat(cities, everyItem(not(isEmptyOrNullString())));

//        Company names
        List<String> companies = jsonPath.getList("users.company.name");
        assertThat(companies, not(empty()));
        assertThat(companies, everyItem(not(isEmptyOrNullString())));

//        Bank card types
        List<String> cardTypes = jsonPath.getList("users.bank.cardType");
        assertThat(cardTypes, not(empty()));
        assertThat(cardTypes, hasItem("Diners Club International"));

//        Crypto coins
        List<String> coins = jsonPath.getList("users.crypto.coin");
        assertThat(coins, hasItem("Bitcoin"));
    }
}
