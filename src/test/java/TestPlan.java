import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;

public class TestPlan {

    @BeforeSuite
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", Utils.CHROME_DRIVER_LOCATION);
    }
    static WebDriver driver;
    static WebPage webPage;

    @BeforeClass
    public static void setUp()
    {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-gpu");
        options.addArguments("--allow-running-insecure-content");
        driver = new ChromeDriver(options);
        webPage = new WebPage(driver);
        driver.manage().window().maximize();
    }

    @Test(testName = "Storage Map Filter Validations")
    public static void openClusterPage(){

        driver.get(Utils.BASE_URL);
        webPage.selectMaps();
        webPage.filterMapName();
        webPage.wrongFilter("test");
        webPage.trueFilter("default");
        webPage.checkColumn(webPage.entries, "100");
        webPage.checkColumn(webPage.names, "default");
    }

    @AfterSuite
    public static void cleanUp(){
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
