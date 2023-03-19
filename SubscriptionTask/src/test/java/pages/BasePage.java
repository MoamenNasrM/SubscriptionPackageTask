package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.List;

public class BasePage {
    WebDriver driver;

    public BasePage(WebDriver dr) { this.driver = dr; }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    public void clickOn(By by) { driver.findElement(by).click(); };

    public String getText(By by){ return driver.findElement(by).getText(); }

    public List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    public boolean isVisible(By by) { return driver.findElement(by).isDisplayed(); }

    public boolean waitExpectedConditionsVisibilityOf(By by, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return isVisible(by);
        } catch (TimeoutException ex) {
            return false;
        }
    }

    public List<String> getListOfTexts(By by) {
        List<WebElement> elements = findElements(by);
        List<String> texts = new ArrayList<>();
        for (WebElement element : elements) {
            texts.add(element.getText());
        }
        return texts;
    }

}
