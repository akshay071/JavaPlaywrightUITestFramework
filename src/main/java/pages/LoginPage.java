package pages;

import com.microsoft.playwright.Page;
import locators.LoginPageLocators;

public class LoginPage {
    private final Page page;

    public LoginPage(Page page){
        this.page = page;
    }

    public void enterUsername(String email){
        page.locator(LoginPageLocators.EMAIL_INPUT).fill(email);
    }

    public void enterPassword(String password){
        page.locator(LoginPageLocators.PASSWORD_INPUT).fill(password);
    }

    public void clickLoginButton(){
        page.locator(LoginPageLocators.SIGNIN_BUTTON).click();
    }

    public void login(String email, String password){
        enterUsername(email);
        enterPassword(password);
        clickLoginButton();
    }
}
