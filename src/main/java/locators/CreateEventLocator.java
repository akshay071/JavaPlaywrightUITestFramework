package locators;

public class CreateEventLocator {
    private  CreateEventLocator(){ }

    public static final String ADMIN_BUTTON = "//button[text()=\"Admin\"]";
    public static final String MANAGE_EVENT_LINK = "//a[text()=\"Manage Events\"]";

    public static final String TITLE_INPUT = "//input[@id=\"event-title-input\"]";
    public static final String DESCRIPTION_TEXTAREA = "//textarea[@placeholder=\"Describe the event…\"]";
    public static final String CATEGORY_DROPDOWN = "//select[@id=\"category\"]";
    public static final String CITY_INPUT = "//input[@id=\"city\"]";
    public static final String VENUE_INPUT = "//input[@id=\"venue\"]";
    public static final String DATE_TIME_INPUT = "//input[@id=\"event-date-&-time\"]";
    public static final String PRICE_INPUT = "//input[@id=\"price-($)\"]";
    public static final String TOTAL_SEATS_INPUT = "//input[@id=\"total-seats\"]";
    public static final String ADD_EVENT_BUTTON =  "//button[@id=\"add-event-btn\"]";
}
