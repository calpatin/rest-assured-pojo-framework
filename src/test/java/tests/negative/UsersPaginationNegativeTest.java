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
                // name, page, expectedSkip, expectEmptyUsers
                {"negative page should fallback to first page", -1, 0, false},
                {"huge page should return empty users list", 999999, null, true}
        };
    }

    @Test(dataProvider = "invalidOrEdgePages")
    public void getUsers_withInvalidOrEdgePages_shouldBehaveAsExpected(
            String caseName,
            int page,
            Integer expectedSkip,
            boolean expectEmptyUsers
    ) {
        Response response = usersClient.getUsersByPage(page);
        response.then().statusCode(200);

        Integer total = response.jsonPath().getObject("total", Integer.class);
        Integer limit = response.jsonPath().getObject("limit", Integer.class);
        Integer skip  = response.jsonPath().getObject("skip", Integer.class);
        List<?> users  = response.jsonPath().getList("users");

        // basic existence
        assertThat(caseName + " -> total should exist", total, notNullValue());
        assertThat(caseName + " -> limit should exist", limit, notNullValue());
        assertThat(caseName + " -> skip should exist", skip, notNullValue());
        assertThat(caseName + " -> users should exist", users, notNullValue());

        // invariants (always)
        assertThat(caseName + " -> total should be >= 0", total, greaterThanOrEqualTo(0));
        assertThat(caseName + " -> limit should be >= 0", limit, greaterThanOrEqualTo(0));
        assertThat(caseName + " -> skip should be >= 0", skip, greaterThanOrEqualTo(0));

        // optional skip assertion
        if (expectedSkip != null) {
            assertThat(caseName + " -> skip", skip, is(expectedSkip));
        }

        // users empty / not empty
        if (expectEmptyUsers) {
            assertThat(caseName + " -> users", users, is(empty()));

            // if empty list, API may legitimately return limit=0 (as you saw)
            assertThat(caseName + " -> limit should be 0 when users is empty", limit, is(0));
        } else {
            assertThat(caseName + " -> users", users, not(empty()));

            // if not empty, limit must be > 0
            assertThat(caseName + " -> limit should be > 0 when users is not empty", limit, greaterThan(0));
            assertThat(caseName + " -> total should be > 0 when users is not empty", total, greaterThan(0));
        }
    }
}
