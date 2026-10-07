package PageObjects;

import Tests.Locators;
import org.openqa.selenium.WebDriver;

public class CartCheckout extends Locators {

    // Локаторы для корзины
    private static final String CART_LINK = "#shopping_cart_container a";
    private static final String CHECKOUT_BUTTON = "#checkout";

    // Локаторы для формы оформления заказа
    private static final String FIRST_NAME_INPUT = "//*[@id='first-name']";
    private static final String LAST_NAME_INPUT = "//*[@id='last-name']";
    private static final String ZIP_INPUT = "//*[@id='postal-code']";
    private static final String CONTINUE_BUTTON = "#continue";

    // Локаторы для завершения заказа
    private static final String FINISH_BUTTON = "#finish";
    private static final String BACK_HOME_BUTTON = "#back-to-products";

    // Локатор для проверки успешного завершения
    private static final String COMPLETE_HEADER = "//*[@class='complete-header']";

    public CartCheckout(WebDriver driver) {
        super(driver);
    }

    public void openCart() {
        clickBySelector(CART_LINK);
    }

    public void goToCheckout() {
        clickBySelector(CHECKOUT_BUTTON);
    }

    public void enterFirstName(String firstName) {
        sendTextByXpath(FIRST_NAME_INPUT, firstName);
    }

    public void enterLastName(String lastName) {
        sendTextByXpath(LAST_NAME_INPUT, lastName);
    }

    public void enterZipCode(String zip) {
        sendTextByXpath(ZIP_INPUT, zip);
    }

    public void clickContinue() {
        clickBySelector(CONTINUE_BUTTON);
    }

    public void clickFinish() {
        clickBySelector(FINISH_BUTTON);
    }

    public void clickBackToProducts() {
        clickBySelector(BACK_HOME_BUTTON);
    }

    public boolean isOrderComplete() {
        return isElementPresent(COMPLETE_HEADER);
    }

    public String getCompleteMessage() {
        return getTextByXpath(COMPLETE_HEADER);
    }

    public void completeOrder(String firstName, String lastName, String zipCode) {
        openCart();
        waitForMillis(1000);
        goToCheckout();
        waitForMillis(1000);

        enterFirstName(firstName);
        enterLastName(lastName);
        enterZipCode(zipCode);

        clickContinue();
        waitForMillis(500);

        clickFinish();
        waitForMillis(2000);
    }
}