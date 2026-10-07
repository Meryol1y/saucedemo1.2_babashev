package Tests;

import PageObjects.CartSort;
import PageObjects.True_LoginTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.List;

public class Sorting implements ITest {

    private WebDriver driver;
    private CartSort cartSort;

    @Override
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        cartSort = new CartSort(driver);
    }

    @Override
    public void execute() {
        driver.get("https://www.saucedemo.com/");

        System.out.println("=== ТЕСТ СОРТИРОВКИ ТОВАРОВ ===\n");

        // Шаг 1: Логин
        System.out.println("Шаг 1: Логин на сайт...");
        True_LoginTest trueLoginTest = new True_LoginTest(driver);
        boolean isSuccess = trueLoginTest.performValidLogin("standard_user", "secret_sauce");
        trueLoginTest.waitForMillis(1000);
        System.out.println(" Логин выполнен\n");

        // Шаг 2: Сортировка по имени (A to Z)
        System.out.println("Шаг 2: Сортировка по имени...");
        cartSort.clickSortDropdown();
        cartSort.waitForMillis(500);
        cartSort.sortByNameAZ();
        cartSort.waitForMillis(1000);

        List<String> namesAZ = cartSort.getProductNames();
        System.out.println("Товары: " + namesAZ);
        boolean isAZ = cartSort.isSortedAZ(namesAZ);
        System.out.println(isAZ ? " Отсортированы по алфавиту (A-Z)" : " Не отсортированы");
        System.out.println();

        // Шаг 3: Сортировка по имени (Z to A)
        System.out.println("Шаг 3: Сортировка по имени...");
        cartSort.clickSortDropdown();
        cartSort.waitForMillis(500);
        cartSort.sortByNameZA();
        cartSort.waitForMillis(1000);

        List<String> namesZA = cartSort.getProductNames();
        System.out.println("Товары: " + namesZA);
        boolean isZA = cartSort.isSortedZA(namesZA);
        System.out.println(isZA ? " Отсортированы по алфавиту (Z-A)" : " Не отсортированы");
        System.out.println();

        // Шаг 4: Сортировка по цене (low to high)
        System.out.println("Шаг 4: Сортировка по цене...");
        cartSort.clickSortDropdown();
        cartSort.waitForMillis(500);
        cartSort.sortByPriceLowToHigh();
        cartSort.waitForMillis(1000);

        List<String> pricesLowHigh = cartSort.getProductPrices();
        System.out.println("Цены: " + pricesLowHigh);
        boolean isLowHigh = cartSort.isSortedLowToHigh(pricesLowHigh);
        System.out.println(isLowHigh ? " Отсортированы по возрастанию цены" : " Не отсортированы");
        System.out.println();

        // Итоговый результат
        System.out.println("\n" + "=".repeat(50));
        if (isAZ && isZA && isLowHigh) {
            System.out.println(" Тест пройден! Все сортировки работают корректно.");
        } else {
            System.out.println(" Тест не пройден! Некоторые сортировки не работают.");
        }
    }

    @Override
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
