package tests.TestByTestNG;


import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.HomePage;


public class TestDemo extends BaseTest {

    BasePage basePage;
    HomePage homePage;

    @Test
    public void isWebsiteOpened() throws Exception {
        basePage = new BasePage(driver);
        homePage = new HomePage(driver);
        Assert.assertEquals(basePage.getCurrentURL(), webSite);
        Assert.assertTrue(homePage.getTextFromHomePageFor("country name").contains("السعودية"), "Page language is not as expected");
    }

}
