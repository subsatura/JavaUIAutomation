package twelfth.task3;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tenth.DriverSetUp;

import java.time.Duration;

public class TestThird {
    static WebDriver driver;
    static WebDriverWait wait;
    static Actions actions;


    @BeforeMethod
    public void startDriver() {
        driver = DriverSetUp.setUpDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        driver.get("https://qa-course-01.andersenlab.com/registration");
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }

    @Test
    public void mainTest() {
    actions.sendKeys(driver.findElement(By.xpath("//*[@id='root']/div/div/div/form/div/div[1]/label/input")), "Sam", Keys.TAB, "Meller")
            .perform();
    actions.click(driver.findElement(By.xpath("//*[@id='root']/div/div/div/form/div/label/div[1]/div/input")))
            .perform();
    Select year = new Select(driver.findElement(By.xpath("//*[@id='root']/div/div/div/form/div/label/div[2]/div[2]/div/div/div[2]/div[1]/div[1]/select[1]")));
    year.selectByValue("1990");
    Select month = new Select(driver.findElement(By.xpath("//*[@id='root']/div/div/div/form/div/label/div[2]/div[2]/div/div/div[2]/div[1]/div[1]/select[2]")));
    month.selectByIndex(7);
    actions.click(driver.findElement(By.xpath("//*[@id='root']/div/div/div/form/div/label/div[2]/div[2]/div/div/div[2]/div[2]/div[2]/div[6]")))
            .click(driver.findElement(By.xpath("//*[@id='root']/div/header")))
            .sendKeys(driver.findElement(By.xpath("//*[@id='root']/div/div/div/form/div/div[3]/label/input")),"sam@sam.sam", Keys.TAB, "password",Keys.TAB, "password")
            .build()
            .perform();
    }
}
