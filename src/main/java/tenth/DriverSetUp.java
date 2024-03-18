package tenth;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverSetUp {
    private static WebDriver driver;

    public static WebDriver setUpDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.setBinary("C:\\chrome-win64\\chrome.exe");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        return driver;
    }

    private static WebDriver getInstance() {
        if (driver == null) {
            try {
                driver = setUpDriver();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
        return driver;
    }

    public static WebDriver startDriver() {
        driver = getInstance();
        return driver;
    }
}

