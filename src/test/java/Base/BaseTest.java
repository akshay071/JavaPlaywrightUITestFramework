package Base;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.LoginPage;
import utils.ConfingReader;

import java.nio.file.Paths;
import java.util.Properties;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeClass
    public void setup(){
        playwright = Playwright.create();
        boolean headless = ConfingReader.getBoolean("headless");
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless));

        context = browser.newContext();
        context.tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true).setSources(true));
        page = context.newPage();

        page.navigate(ConfingReader.get("baseUrl"));

        login();
    }

    private void login() {
        LoginPage loginPage = new LoginPage(page);

        loginPage.login(
                ConfingReader.get("email"),
                ConfingReader.get("password")
        );

        assertThat(
                page.getByRole(
                        AriaRole.LINK,
                        new Page.GetByRoleOptions().setName("Browse Events →")
                )
        ).isVisible();

        System.out.println("Login Completed Successfully");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){


        context.tracing().stop(new Tracing.StopOptions().setPath(Paths.get("trace.zip")));

        if(context != null){
            context.close();
        }

        if(browser != null){
            browser.close();
        }

        if(playwright != null){
            playwright.close();
        }

    }
}
