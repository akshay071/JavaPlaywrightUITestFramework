package tests;

import Base.BaseTest;
import com.microsoft.playwright.Page;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import com.microsoft.playwright.options.AriaRole;
import pages.CreateEvent;
import utils.ExcelUtil;

import java.util.Map;

public class LoginTest extends BaseTest {

    private CreateEvent createEvent;
    @BeforeClass
    public void initializePage(){
        createEvent = new CreateEvent(page);
    }

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

    @Test(dependsOnMethods = "testLogin")
    public void createEvent(){
        Map<String, String> data = ExcelUtil.getTestData("TC01");
        String eventName = data.get("EventName");
        String description = data.get("Description");
        String category = data.get("Category");
        String city = data.get("City");
        String venue = data.get("Venue");
        String dateTime = data.get("DateTime");
        String price = data.get("Price");
        String totalSeats = data.get("TotalSeats");

        createEvent.navigateManageEvent();
        createEvent.createEvent(eventName,description,category,city,venue,dateTime,price,totalSeats);

    }
}
