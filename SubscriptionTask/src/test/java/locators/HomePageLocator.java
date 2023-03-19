package locators;

import org.openqa.selenium.By;

public class HomePageLocator {

    public static By PAGE_LANGUAGE = By.id("translation-btn");
    public static By COUNTRY_NAME = By.id("country-name");
    public static By SUBSCRIPTION_PACKAGES_TYPES = By.xpath("//strong[contains(@id, 'name-')]");
    public static By SUBSCRIPTION_PACKAGES_PRICES = By.xpath("//div[contains(@id, 'currency-')]/b");
    public static By SUBSCRIPTION_PACKAGES_CURRENCY = By.xpath("//div[contains(@id, 'currency-')]/i");
}
