package Tests;

import PageObjects.Neg_LoginTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.jupiter.api.Assertions.*;

public class Neg_Registration implements ITest {

    private WebDriver driver;
    private Neg_LoginTest negLoginTest;

    @Override
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        negLoginTest = new Neg_LoginTest(driver);
    }

    @Override
    public void execute() {
        driver.get("https://www.saucedemo.com/");

        System.out.println("=== NEGATIVE LOGIN TEST ===\n");

        // Негативный тест: вход с некорректным username
        System.out.println("Попытка входа с некорректным username...");

        negLoginTest.performInvalidLogin("tandard_user", "secret_sauce");
        negLoginTest.waitForMillis(1000);

        assertTrue(
                negLoginTest.isErrorMessageDisplayed(),
                "Ожидалось: сообщение об ошибке отображается\nФактически: сообщение не появилось"
        );

        String errorMessage = negLoginTest.getErrorMessage();
        assertEquals(
                "Epic sadface: Username and password do not match any user in this service",
                errorMessage,
                "Ожидалось: стандартное сообщение об ошибке\nФактически: " + errorMessage
        );

        System.out.println("✅ Тест пройден! Ошибка корректно отображена: " + errorMessage);
    }

    @Override
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}