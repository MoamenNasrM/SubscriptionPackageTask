package pages;

import static locators.HomePageLocator.*;
import org.openqa.selenium.WebDriver;


import java.util.List;

public class HomePage {

    WebDriver driver;
    BasePage basePage;

    public HomePage(WebDriver dr) {
        this.driver = dr;
    }

    public void navigateToURL(String country) throws Exception {
        String countryIndicator;
        switch (country.toLowerCase()) {
            case "saudi arabia" :
                countryIndicator = "sa";
                break;
            case "bahrain" :
                countryIndicator = "bh";
                break;
            case "kuwait" :
                countryIndicator = "kw";
                break;
            default:
                throw new Exception("Unexpected language: " + country);
        }
        driver.navigate().to("https://subscribe.stctv.com/" + countryIndicator + "-en");
    }

    public boolean isThePageOpened(){
        basePage = new BasePage(driver);
        return basePage.waitExpectedConditionsVisibilityOf(COUNTRY_NAME, 5);
    }

    public String getTextFromHomePageFor(String field) throws Exception {
        basePage = new BasePage(driver);
        switch (field.toLowerCase()) {
            case "page language" :
                return basePage.getText(PAGE_LANGUAGE);
            case "country name" :
                return basePage.getText(COUNTRY_NAME);
            default:
                throw new Exception("Unexpected field: " + field);
        }
    }

    public List<String> getDataForSubscriptionPackages(String field) throws Exception {
        basePage = new BasePage(driver);
        switch (field.toLowerCase()) {
            case "subscription packages types" :
                return basePage.getListOfTexts(SUBSCRIPTION_PACKAGES_TYPES);
            case "subscription packages prices" :
                return basePage.getListOfTexts(SUBSCRIPTION_PACKAGES_PRICES);
            case "subscription packages currency" :
                return basePage.getListOfTexts(SUBSCRIPTION_PACKAGES_CURRENCY);
            default:
                throw new Exception("Unexpected field: " + field);
        }
    }

}
