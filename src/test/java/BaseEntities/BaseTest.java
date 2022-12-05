package BaseEntities;

import configuration.ReadProperties;
import configuration.UpdateEnvironmentProperties;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import services.BrowsersService;
import utils.InvokedListener;

@Listeners(InvokedListener.class)
public class BaseTest {
    protected WebDriver driver;
    private Capabilities capabilities;


    @BeforeMethod
    public void setUp(ITestContext iTestContext){
        driver = new BrowsersService().getDriver();
        iTestContext.setAttribute("driver", driver);

        driver.get(ReadProperties.getUrl());
        capabilities = ((RemoteWebDriver) driver).getCapabilities();

        UpdateEnvironmentProperties.setProperty("os.name", System.getProperty("os.name"));
        UpdateEnvironmentProperties.setProperty("user.name", System.getProperty("user.name"));
        UpdateEnvironmentProperties.setProperty("java.version", System.getProperty("java.version"));
        UpdateEnvironmentProperties.setProperty("browser.name", capabilities.getBrowserName());
        UpdateEnvironmentProperties.setProperty("browser.version", capabilities.getBrowserVersion());
    }

/*    @BeforeMethod
    public void setEnvironmentProperties(){
        UpdateEnvironmentProperties.setProperty("os.name", System.getProperty("os.name"));
        UpdateEnvironmentProperties.setProperty("user.name", System.getProperty("user.name"));
        UpdateEnvironmentProperties.setProperty("java.version", System.getProperty("java.version"));
        UpdateEnvironmentProperties.setProperty("browser.name", capabilities.getBrowserName());
        UpdateEnvironmentProperties.setProperty("browser.version", capabilities.getBrowserVersion());
    }*/

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @AfterTest
    public void storeInfo() {
        UpdateEnvironmentProperties.storeEnvProperties();
    }
}