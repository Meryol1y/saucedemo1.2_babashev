package Tests;

import PageObjects.CartAdd;
import PageObjects.True_LoginTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.jupiter.api.Assertions.*;

public class CartObjAdd implements ITest {

    private WebDriver driver;
    private CartAdd cartAdd;

    @Override
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        cartAdd = new CartAdd(driver);
    }

    @Override
    public void execute() {
        driver.get("https://www.saucedemo.com/");

        System.out.println("=== ТЕСТ ДОБАВЛЕНИЯ ТОВАРОВ В КОРЗИНУ ===\n");

        // Сначала логинимся (т.к. без логина товары не добавятся)
        System.out.println("Шаг 1: Логин на сайт...");
        True_LoginTest trueLoginTest = new True_LoginTest(driver);
        boolean isSuccess = trueLoginTest.performValidLogin("standard_user", "secret_sauce");
        trueLoginTest.waitForMillis(1000);
        System.out.println("✅ Логин выполнен\n");

        // Добавляем товары по одному с проверкой счетчика
        System.out.println("Шаг 2: Добавление товаров в корзину...");

        cartAdd.addBackpack();
        System.out.println("1. Добавлен: Sauce Labs Backpack");
        System.out.println("   Счетчик корзины: " + cartAdd.getCartCount());

        cartAdd.addBikeLight();
        System.out.println("2. Добавлен: Sauce Labs Bike Light");
        System.out.println("   Счетчик корзины: " + cartAdd.getCartCount());

        cartAdd.addBoltTShirt();
        System.out.println("3. Добавлен: Sauce Labs Bolt T-Shirt");
        System.out.println("   Счетчик корзины: " + cartAdd.getCartCount());

        cartAdd.addFleeceJacket();
        System.out.println("4. Добавлен: Sauce Labs Fleece Jacket");
        System.out.println("   Счетчик корзины: " + cartAdd.getCartCount());

        // Проверка результата
        System.out.println("\n" + "=".repeat(50));
        String cartCount = cartAdd.getCartCount();

        String СartCount = cartAdd.getCartCount();
        assertEquals(
                "4",
                cartCount,
                "Ожидалось: в корзине 4 товара\nФактически: в корзине " + cartCount + " товара"
        );

        assertTrue(
                cartAdd.isCartBadgeDisplayed(),
                "Ожидалось: счетчик корзины отображается\nФактически: счетчик не виден"
        );

        System.out.println("✅ Тест пройден! В корзине " + cartCount + " товара.");
    }

    @Override
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}