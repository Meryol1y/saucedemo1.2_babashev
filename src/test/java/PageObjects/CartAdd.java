package PageObjects;

import Tests.Locators;
import org.openqa.selenium.WebDriver;

public class CartAdd extends Locators {

    // Локаторы кнопок добавления товаров
    private static final String BACKPACK_BUTTON = "#add-to-cart-sauce-labs-backpack";
    private static final String BIKE_LIGHT_BUTTON = "#add-to-cart-sauce-labs-bike-light";
    private static final String BOLT_TSHIRT_BUTTON = "#add-to-cart-sauce-labs-bolt-t-shirt";
    private static final String FLEECE_JACKET_BUTTON = "#add-to-cart-sauce-labs-fleece-jacket";

    // Локатор счетчика корзины
    private static final String CART_BADGE = ".shopping_cart_badge";

    // Локатор иконки корзины (для перехода в корзину)
    private static final String CART_ICON = ".shopping_cart_link";

    public CartAdd(WebDriver driver) {
        super(driver);
    }

    public void addBackpack() {
        clickBySelector(BACKPACK_BUTTON);
    }

    public void addBikeLight() {
        clickBySelector(BIKE_LIGHT_BUTTON);
    }

    public void addBoltTShirt() {
        clickBySelector(BOLT_TSHIRT_BUTTON);
    }

    public void addFleeceJacket() {
        clickBySelector(FLEECE_JACKET_BUTTON);
    }

    public String getCartCount() {
        return getTextBySelector(CART_BADGE);
    }

    public boolean isCartBadgeDisplayed() {
        return isElementPresent(CART_BADGE);
    }

    public void openCart() {
        clickBySelector(CART_ICON);
    }

    public void addAllProducts() {
        addBackpack();
        addBikeLight();
        addBoltTShirt();
        addFleeceJacket();
    }
}
