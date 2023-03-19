package tests.TestByTestNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pages.BasePage;


import static locators.HomePageLocator.*;


public class BaseTest {

    public String webSite = "https://subscribe.stctv.com/sa-ar";
    public WebDriver driver;


    @BeforeTest
    public void initializeTestRun()
    {
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(chromeOptions);
        driver.navigate().to(webSite);
        driver.manage().window().maximize();
        BasePage basePage = new BasePage(driver);
        basePage.waitExpectedConditionsVisibilityOf(COUNTRY_NAME, 5);
    }


    @AfterTest
    public void quit() { driver.quit(); }


}
