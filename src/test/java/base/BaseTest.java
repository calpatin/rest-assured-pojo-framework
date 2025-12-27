package base;

public abstract class BaseTest {

    // Helper class for future extension
    protected String randomEmail() {
        return "user_" + System.currentTimeMillis() + "@test.com";
    }

    protected int randomAge() {
        return 18 + (int) (Math.random() * 40);
    }
}
