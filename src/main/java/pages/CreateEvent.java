package pages;

import com.microsoft.playwright.Page;
import locators.CreateEventLocator;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.setDefaultAssertionTimeout;

public class CreateEvent {
    private final Page page;

    public CreateEvent(Page page){ this.page = page;}

    public void enterTitle(String title){
        page.locator(CreateEventLocator.TITLE_INPUT).fill(title);
    }

    public void enterDescription(String description){
        page.locator(CreateEventLocator.DESCRIPTION_TEXTAREA).fill(description);
    }

    public void selectCategory(String category){
        page.locator(CreateEventLocator.CATEGORY_DROPDOWN).selectOption(category);
    }

    public void  enterCity(String city){
        page.locator(CreateEventLocator.CITY_INPUT).fill(city);
    }

    public void enterVenue(String venue){
        page.locator(CreateEventLocator.VENUE_INPUT).fill(venue);
    }

    public void enterDateTime(String dateTime){
        page.locator(CreateEventLocator.DATE_TIME_INPUT).fill(dateTime);
    }

    public void enterPrice(String price){
        page.locator(CreateEventLocator.PRICE_INPUT).fill(price);
    }

    public void enterToatlSeats(String seats){
        page.locator(CreateEventLocator.TOTAL_SEATS_INPUT).fill(seats);
    }

    public void clickAddEvent(){
        assertThat(page.locator(CreateEventLocator.ADD_EVENT_BUTTON)).isVisible();
        page.locator(CreateEventLocator.ADD_EVENT_BUTTON).click();
    }

    public void navigateManageEvent(){
        page.locator(CreateEventLocator.ADMIN_BUTTON).click();
        setDefaultAssertionTimeout(10000);
        assertThat(page.locator(CreateEventLocator.MANAGE_EVENT_LINK).first()).isVisible();
        page.locator(CreateEventLocator.MANAGE_EVENT_LINK).first().click();
    }

    public void createEvent(String title, String description, String category, String city, String venue, String date_time, String seats, String price){
        enterTitle(title);
        enterDescription(description);
        selectCategory(category);
        enterCity(city);
        enterVenue(venue);
        enterDateTime(date_time);
        enterPrice(price);
        enterToatlSeats(seats);
        clickAddEvent();
        assertThat(page.getByText("Event created!"));
        System.out.println("Event Created");
        assertThat(page.getByText("All Events")).isVisible();
        assertThat(page.getByText(title)).isVisible();
    }
}
