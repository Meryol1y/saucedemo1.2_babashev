package Tests;

import PageObjects.CartCheckout;
import PageObjects.CartAdd;
import PageObjects.True_LoginTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.jupiter.api.Assertions.*;

public class OrderComp implements ITest {

    private WebDriver driver;
    private CartCheckout cartCheckout;
    private CartAdd cartAdd;

    @Override
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        cartCheckout = new CartCheckout(driver);
        cartAdd = new CartAdd(driver);
    }

    @Override
    public void execute() {
        driver.get("https://www.saucedemo.com/");

        System.out.println("=== ТЕСТ ОФОРМЛЕНИЯ ЗАКАЗА ===\n");

        // Шаг 1: Логин
        System.out.println("Шаг 1: Логин на сайт...");
        True_LoginTest trueLoginTest = new True_LoginTest(driver);
        boolean isSuccess = trueLoginTest.performValidLogin("standard_user", "secret_sauce");
        trueLoginTest.waitForMillis(1000);
        System.out.println(" Логин выполнен\n");

        // Шаг 2: Добавить товары в корзину
        System.out.println("Шаг 2: Добавление товаров в корзину...");
        cartAdd.addBackpack();
        cartAdd.addBikeLight();
        cartAdd.addBoltTShirt();
        cartAdd.addFleeceJacket();

        // Шаг 3: Оформить заказ
        System.out.println("Шаг 3: Оформление заказа...");
        cartCheckout.completeOrder("Nikita", "Babashev", "123");
        System.out.println(" Заказ оформлен\n");

        // Шаг 4: Проверка успешного завершения
        System.out.println("Шаг 4: Проверка результата...");
        boolean isComplete = cartCheckout.isOrderComplete();

        assertTrue(
                cartCheckout.isOrderComplete(),
                "Ожидалось: заказ успешно оформлен\nФактически: заказ не оформлен"
        );

        String completeMessage = cartCheckout.getCompleteMessage();
        assertEquals(
                "Thank you for your order!",
                completeMessage,
                "Ожидалось: 'Thank you for your order!'\nФактически: '" + completeMessage + "'"
        );

        System.out.println("✅ Тест пройден! " + completeMessage);

        // Шаг 5: Вернуться к товарам
        System.out.println("\nШаг 5: Возврат к списку товаров...");
        cartCheckout.clickBackToProducts();
        cartCheckout.waitForMillis(3000);
        System.out.println(" Вернулись к товарам");
    }

    @Override
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}