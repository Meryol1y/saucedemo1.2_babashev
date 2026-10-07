package PageObjects;

import Tests.Locators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;
import java.util.ArrayList;

public class CartSort extends Locators {

    // Локатор выпадающего списка сортировки
    private static final String SORT_DROPDOWN = "#header_container > div.header_secondary_container > div > span > select";

    // Локаторы для товаров (названия и цены)
    private static final String PRODUCT_NAMES = ".inventory_item_name";
    private static final String PRODUCT_PRICES = ".inventory_item_price";

    public CartSort(WebDriver driver) {
        super(driver);
    }

    public void clickSortDropdown() {
        clickBySelector(SORT_DROPDOWN);
    }

    public void selectSortOption(int optionIndex) {
        String optionSelector = SORT_DROPDOWN + " > option:nth-child(" + optionIndex + ")";
        clickBySelector(optionSelector);
    }

    public void sortByNameAZ() {
        selectSortOption(2); // option:nth-child(2)
    }

    public void sortByNameZA() {
        selectSortOption(3); // option:nth-child(3)
    }

    public void sortByPriceLowToHigh() {
        selectSortOption(4); // option:nth-child(4)
    }

    public void sortByPriceHighToLow() {
        selectSortOption(5); // option:nth-child(5)
    }

    public List<String> getProductNames() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(PRODUCT_NAMES)));

        List<WebElement> elements = driver.findElements(By.cssSelector(PRODUCT_NAMES));
        List<String> names = new ArrayList<>();
        for (WebElement element : elements) {
            names.add(element.getText());
        }
        return names;
    }

    public List<String> getProductPrices() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(PRODUCT_PRICES)));

        List<WebElement> elements = driver.findElements(By.cssSelector(PRODUCT_PRICES));
        List<String> prices = new ArrayList<>();
        for (WebElement element : elements) {
            prices.add(element.getText().replace("$", ""));
        }
        return prices;
    }

    public boolean isSortedAZ(List<String> names) {
        List<String> sorted = new ArrayList<>(names);
        sorted.sort(String::compareTo);
        return sorted.equals(names);
    }

    public boolean isSortedZA(List<String> names) {
        List<String> sorted = new ArrayList<>(names);
        sorted.sort((a, b) -> b.compareTo(a));
        return sorted.equals(names);
    }

    public boolean isSortedLowToHigh(List<String> prices) {
        List<Double> numericPrices = new ArrayList<>();
        for (String price : prices) {
            numericPrices.add(Double.parseDouble(price));
        }
        List<Double> sorted = new ArrayList<>(numericPrices);
        java.util.Collections.sort(sorted);
        return sorted.equals(numericPrices);
    }

    public boolean isSortedHighToLow(List<String> prices) {
        List<Double> numericPrices = new ArrayList<>();
        for (String price : prices) {
            numericPrices.add(Double.parseDouble(price));
        }
        List<Double> sorted = new ArrayList<>(numericPrices);
        sorted.sort(java.util.Collections.reverseOrder());
        return sorted.equals(numericPrices);
    }
}