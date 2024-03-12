package eleventh.task4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tenth.DriverSetUp;

import java.time.Duration;

public class task4 {

    @Test(dataProvider = "data")
    public void testMethodWithDataProvider(String login, String pass) {
        WebDriver driver;
        driver = DriverSetUp.setUpDriver();
        driver.get("https://qa-course-01.andersenlab.com/login");
        driver.findElement(By.xpath("//*[@id='root']/div/div/div/form/div/div[1]/label/input")).sendKeys(login);
        driver.findElement(By.xpath("//*[@id='root']/div/div/div/form/div/div[2]/label/input")).sendKeys(pass);
        driver.findElement(By.xpath("//*[@id='root']/div/div/div/form/button")).click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlToBe("https://qa-course-01.andersenlab.com/"));
        Assert.assertEquals(driver.getCurrentUrl(), "https://qa-course-01.andersenlab.com/");
        driver.close();
    }

    @DataProvider(name = "data")
    public Object[][] providedData() {
        return new Object[][]{
                {"subsatura@gmail.com", "21121488"},
                {"virsli123@gmail.com", "kukorica11"},
                {"csinalni@gmail.com", "bassyus32"},
        };
    }

}
