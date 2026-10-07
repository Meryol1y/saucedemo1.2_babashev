package PageObjects;

import Tests.Locators;
import org.openqa.selenium.WebDriver;

public class Neg_LoginTest extends Locators {

    // Локаторы для страницы логина
    private static final String USERNAME_INPUT = "//*[@id='user-name']";
    private static final String PASSWORD_INPUT = "//*[@id='password']";
    private static final String LOGIN_BUTTON = "//input[@type='submit']";
    private static final String ERROR_MESSAGE = "//h3[@data-test='error']";

    public Neg_LoginTest(WebDriver driver) {
        super(driver);
    }

    public void enterInvalidUsername(String username) {
        sendTextByXpath(USERNAME_INPUT, username);
    }

    public void enterPassword(String password) {
        sendTextByXpath(PASSWORD_INPUT, password);
    }

    public void clickLoginButton() {
        clickByXpath(LOGIN_BUTTON);
    }

    public String getErrorMessage() {
        return getTextByXpath(ERROR_MESSAGE);
    }

    public boolean isErrorMessageDisplayed() {
        return isElementPresent(ERROR_MESSAGE);
    }

    public void performInvalidLogin(String username, String password) {
        enterInvalidUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
}