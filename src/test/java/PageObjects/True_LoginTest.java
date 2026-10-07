package PageObjects;

import Tests.Locators;
import org.openqa.selenium.WebDriver;

public class True_LoginTest extends Locators {

    // Локаторы для страницы логина
    private static final String USERNAME_INPUT = "//*[@id='user-name']";
    private static final String PASSWORD_INPUT = "//*[@id='password']";
    private static final String LOGIN_BUTTON = "//input[@type='submit']";
    private static final String PRODUCTS_TITLE = "//span[@class='title']";

    public True_LoginTest(WebDriver driver) {
        super(driver);
    }

    public void enterValidUsername(String username) {
        sendTextByXpath(USERNAME_INPUT, username);
    }

    public void enterPassword(String password) {
        sendTextByXpath(PASSWORD_INPUT, password);
    }

    public void clickLoginButton() {
        clickByXpath(LOGIN_BUTTON);
    }

    public boolean isSuccessLogin() {
        return isElementPresent(PRODUCTS_TITLE);
    }

    public boolean performValidLogin(String username, String password) {
        enterValidUsername(username);
        enterPassword(password);
        clickLoginButton();
        return isSuccessLogin();
    }
}