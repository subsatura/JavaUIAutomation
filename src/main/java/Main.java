import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Set ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");

        // Configure Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.setBinary("C:\\chrome-win64\\chrome.exe");

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        // Navigate to the website
        driver.get("http://www.automationpractice.pl/index.php");

        // Search for the first item
        searchAndClick(driver, "Printed chiffon dress");

        // Navigate back to the homepage
        driver.navigate().back();

        // Search for the second item
        searchAndClick(driver, "Faded Short");

        // Close the browser
        driver.quit();
    }

    public static void searchAndClick(WebDriver driver, String searchText) {
        // Enter search text
        WebElement searchBox = driver.findElement(By.id("search_query_top"));
        searchBox.clear();
        searchBox.sendKeys(searchText);

        // Click search button
        driver.findElement(By.cssSelector("#searchbox > button")).click();

        // Click on the list view
        driver.findElement(By.cssSelector("#list > a > i")).click();

        // Click on the first item
        driver.findElement(By.cssSelector("#center_column > ul > li:nth-child(1) > div > div > div.right-block > div.button-container > a")).click();
    }
}
