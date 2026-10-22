package tests;

import Base.BaseTest;
import com.microsoft.playwright.Page;
import org.testng.annotations.Test;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import com.microsoft.playwright.options.AriaRole;

public class LoginTest extends BaseTest {

    @Test
    public void testLogin() {
        // Verify that the login was successful by checking the visibility of a specific element
        assertThat(
                page.getByRole(
                        AriaRole.LINK,
                        new Page.GetByRoleOptions().setName("Browse Events →")
                )
        ).isVisible();

        System.out.println("Login Test Passed");
    }
}
