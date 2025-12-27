package tests.negative;

import base.BaseTest;
import clients.UsersClient;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UsersPaginationNegativeTest extends BaseTest {

    private final UsersClient usersClient = new UsersClient();

    @DataProvider(name = "invalidOrEdgePages")
    public Object[][] invalidOrEdgePages() {
        return new Object[][]{
                {"negative page -> fallback to first page", -1, 0, false},
                {"huge page -> empty users list", 999999, null, true}
        };
    }

    @Test(dataProvider = "invalidOrEdgePages")
    public void getUsers_withInvalidOrEdgePages_shouldRespectPaginationContract(
            String caseName,
            int page,
            Integer expectedSkip,
            boolean expectEmptyUsers
    ) {
        // Act
        Response response = usersClient.getUsersByPage(page);

        // Assert: status
        response.then().statusCode(200);

        // Extract once
        Integer total = response.jsonPath().getObject("total", Integer.class);
        Integer limit = response.jsonPath().getObject("limit", Integer.class);
        Integer skip  = response.jsonPath().getObject("skip", Integer.class);
        List<?> users = response.jsonPath().getList("users");

        // Assert: fields exist
        assertThat(caseName + " -> total exists", total, notNullValue());
        assertThat(caseName + " -> limit exists", limit, notNullValue());
        assertThat(caseName + " -> skip exists", skip, notNullValue());
        assertThat(caseName + " -> users exists", users, notNullValue());

        // Assert: invariants
        assertThat(caseName + " -> total >= 0", total, greaterThanOrEqualTo(0));
        assertThat(caseName + " -> limit >= 0", limit, greaterThanOrEqualTo(0));
        assertThat(caseName + " -> skip >= 0", skip, greaterThanOrEqualTo(0));

        // Assert: expected skip (only when relevant)
        if (expectedSkip != null) {
            assertThat(caseName + " -> skip", skip, is(expectedSkip));
        }

        // Assert: behavior
        if (expectEmptyUsers) {
            assertThat(caseName + " -> users empty", users, is(empty()));
            assertThat(caseName + " -> limit == 0 when users empty", limit, is(0));
        } else {
            assertThat(caseName + " -> users not empty", users, not(empty()));
            assertThat(caseName + " -> limit > 0 when users not empty", limit, greaterThan(0));
            assertThat(caseName + " -> total > 0 when users not empty", total, greaterThan(0));
        }
    }
}
