package tenth.fourth;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.setBinary("C:\\chrome-win64\\chrome.exe");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("http://www.automationpractice.pl/index.php");
        driver.findElement(By.id("search_query_top")).sendKeys("Printed chiffon dress");
        driver.findElement(By.xpath("//*[@id='searchbox']/button")).click();
        driver.findElement(By.xpath("//*[@id='list']/a/i")).click();
        driver.findElement(By.xpath("//*[@id='center_column']/ul/li[1]/div/div/div[3]/div/div[3]/div/a")).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id='block_top_menu']/ul/li[1]/a")).click();
        driver.findElement(By.xpath("//*[@id='search_query_top']")).sendKeys("Faded Short");
        driver.findElement(By.xpath("//*[@id='searchbox']/button")).click();
        driver.findElement(By.xpath("//*[@id='list']/a/i")).click();
        driver.findElement(By.xpath("//*[@id='center_column']/ul/li[1]/div/div/div[3]/div/div[3]/div/a")).click();
        driver.findElement(By.xpath("//*[@id='center_column']/div[1]/div[2]/form/button/span")).click();
        Thread.sleep(3000);
        driver.close();
        driver.quit();
    }
}
