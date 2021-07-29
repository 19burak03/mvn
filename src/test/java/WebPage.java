import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;

public class WebPage extends PageObjects{

    @FindBy(xpath = "//a[contains(text(),'Select')]")
    private WebElement active_cluster;

    @FindBy(xpath = "//div[contains(text(),'dev')]")
    private WebElement cluster_header;
    
    @FindBy(xpath = "//button[contains(text(),'Add Cluster Config')]")
    private WebElement cluster_config;
    
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement save_config;

    @FindBy(xpath = "//a[@data-test='menu-maps']/span[contains(text(),'Maps')]")
    private WebElement storage_maps;

    @FindBy(css = "div.rt-tbody div.rt-tr-group div.rt-td.core-components-HzTable-__HzTable-module___left.core-components-HzTable-__HzTable-module___box")
    WebElement names;

    @FindBy(css = "div.rt-tbody div.rt-tr-group div.rt-td.core-components-HzTable-__HzTable-module___left.core-components-HzTable-__HzTable-module___box + div > div")
    WebElement entries;

    @FindBy(xpath = "//span[contains(text(),'No data available in table')]")
    private WebElement no_data_filter;
    
    @FindBy(xpath = "//span[contains(text(),'ENABLE')]")
    private WebElement enable_dev_mode;

    @FindBy(xpath = "//input[@type='text']")
    private WebElement name_filter;

    public WebPage(WebDriver driver) {
        super(driver);
    }

    public void selectCluster(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(this.active_cluster));
        this.active_cluster.click();
    }

    public void clusterConfig(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(this.cluster_config));
        this.cluster_config.click();
    }
    
    public void saveConfig(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(this.save_config));
        this.save_config.click();    
    }

    public void selectMaps(){
        Actions action = new Actions(driver);
        action.moveToElement(storage_maps).perform();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(this.storage_maps));
        this.storage_maps.click();
    }
    
    public void enableDevMode() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(this.enable_dev_mode));
        this.enable_dev_mode.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    
    public void filterMapName(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOf(this.enable_dev_mode));
        this.name_filter.isDisplayed();
    }

    public void trueFilter(String name){
        this.name_filter.clear();
        this.name_filter.sendKeys(name);
        String map_names;
        map_names = this.names.getText();
        Assert.assertEquals(map_names, name);
    }

    public void wrongFilter(String name){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        this.name_filter.clear();
        this.name_filter.sendKeys(name);
        this.no_data_filter.isDisplayed();
    }

    public void checkColumn(WebElement columnPath, String value){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertEquals(columnPath.getText(), value);
    }

}
