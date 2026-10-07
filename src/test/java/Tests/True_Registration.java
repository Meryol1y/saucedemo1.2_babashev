package Tests;

import PageObjects.True_LoginTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.jupiter.api.Assertions.*;

public class True_Registration implements ITest {

    private WebDriver driver;
    private True_LoginTest trueLoginTest;

    @Override
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        trueLoginTest = new True_LoginTest(driver);
    }

    @Override
    public void execute() {
        driver.get("https://www.saucedemo.com/");

        System.out.println("=== POSITIVE LOGIN TEST ===\n");

        // Позитивный тест: вход с корректными данными
        System.out.println("Попытка входа с корректными данными...");

        boolean isSuccess = trueLoginTest.performValidLogin("standard_user", "secret_sauce");
        trueLoginTest.waitForMillis(1000);

        assertTrue(
                isSuccess,
                "Ожидалось: успешный вход выполнен\nФактически: вход не выполнен"
        );

        System.out.println("✅ Тест пройден! Успешный вход выполнен!");
    }

    @Override
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}