package cucumber.stepdefs;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import pages.HomePage;
import readers.ExcelReader;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;


public class HomePageStepDef {

    WebDriver driver;

    @Before
    public void setUp(){
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
    }


    @Given("user navigate to stc website by (.*)")
    public void navigateToURLWithCountry(String country) throws Exception {
        HomePage homePage = new HomePage(driver);
        homePage.navigateToURL(country);

    }

    @When("the page is opened successfully")
    public void isThePageOpenedSuccessfully() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isThePageOpened(), "Page is not opened as expected");
    }

    @Then("(.*) subscription package should be displayed with its details")
    public void verifyTheSpecficSubscriptionPackageDetails(String packageName, DataTable table) throws Exception {
        HomePage homePage = new HomePage(driver);
        int packageNumber;

        switch (packageName.toLowerCase()) {
            case "lite":
                packageNumber = 0;
                break;
            case "classic":
                packageNumber = 1;
                break;
            case "premium":
                packageNumber = 2;
                break;
            default:
                throw new Exception("Unexpected package name: " + packageName);
        }

        List<Map<String, String>> packageData = table.asMaps(String.class, String.class);
        IntStream.range(0, table.asMaps(String.class, String.class).size()).forEach(index -> {
            try {
                if (packageData.get(index).containsKey("Price")) {
                    Assert.assertEquals(homePage.getDataForSubscriptionPackages("Subscription packages prices").get(packageNumber), packageData.get(index).get("Price"), "Subscription packages types is not same");

                } else if (packageData.get(index).containsKey("Currency")) {
                    Assert.assertEquals(homePage.getDataForSubscriptionPackages("Subscription packages currency").get(packageNumber), packageData.get(index).get("Price"), "Subscription packages types is not same");
                  }
                }
            catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    @Then("the subscription packages should be displayed with its details for (.*) like expected data in excel file")
    public void verifyTheSubscriptionPackagesDetails(String countryName) throws Exception {
        HomePage homePage = new HomePage(driver);
        ExcelReader excelReader = new ExcelReader();
        String fileName = "Subscribtion Packages.xlsx";
        int sheetNumber = 0;
        switch (countryName.toLowerCase()){
            case "saudi arabia" :
                sheetNumber = 0;
                break;
            case "bahrain" :
                sheetNumber = 1;
                break;
            case "kuwait" :
                sheetNumber = 2;
                break;
            default:
                throw new Exception("Unexpected language: " + countryName);
        }
        for (int i=0; i<homePage.getDataForSubscriptionPackages("Subscription packages types").size(); i++){
            Assert.assertEquals(homePage.getDataForSubscriptionPackages("Subscription packages types").get(i), excelReader.getData(fileName, sheetNumber,i+1,0).toString(), "Subscription packages types is not same");
            Assert.assertTrue(Double.valueOf(homePage.getDataForSubscriptionPackages("Subscription packages prices").get(i)).equals(excelReader.getData(fileName, sheetNumber,i+1,1)), "Subscription packages prices is not same");
            Assert.assertEquals(homePage.getDataForSubscriptionPackages("Subscription packages currency").get(i), excelReader.getData(fileName, sheetNumber,i+1,2).toString(), "Subscription packages currency is not same");

        }
    }


    @After
    public void quitBrowser(){
        driver.quit();
    }
}
