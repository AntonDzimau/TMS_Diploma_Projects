package services;

import configuration.ReadProperties;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class BrowsersService {
    private WebDriver driver = null;

    public BrowsersService() {
        switch (ReadProperties.browserName().toLowerCase()) {
            case "chrome":
                DriverManagerType driverManagerType = DriverManagerType.CHROME;
                WebDriverManager.getInstance(driverManagerType).setup();

                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setHeadless(ReadProperties.isHeadless());
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--window-size=1024,768");
                chromeOptions.addArguments("--ignore-certificate-errors");
                chromeOptions.addArguments("--silent");
                //chromeOptions.addArguments("--start-maximized");

                driver = new ChromeDriver(chromeOptions);

                break;
            case "firefox":
                driverManagerType = DriverManagerType.FIREFOX;
                WebDriverManager.getInstance(driverManagerType).setup();

                driver = new FirefoxDriver();
            case "edge":
                driverManagerType = DriverManagerType.EDGE;
                WebDriverManager.getInstance(driverManagerType).setup();

                driver = new EdgeDriver();
                break;
            default:
                System.out.println("Browser " + ReadProperties.browserName() + " is not supported.");
                break;
        }
    }

    public WebDriver getDriver() {
        //driver.manage().window().setSize(new Dimension(1024, 768));
        //driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        //неявные ожидания
        //нельзя использовать явные и неявные ожидания одновременно
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

        return driver;
    }
}
