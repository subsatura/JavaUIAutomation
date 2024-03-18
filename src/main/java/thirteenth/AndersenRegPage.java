package thirteenth;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AndersenRegPage {
    private final WebDriver driver;
    private final Actions actions;
    private final WebDriverWait wait;


    public AndersenRegPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    }


    public AndersenRegPage goTo() {
        driver.get("https://qa-course-01.andersenlab.com/registration");
        return this;
    }

    public AndersenRegPage enterFirstName(String firstName) {
        actions.sendKeys(driver.findElement(By.xpath("//*[@id='root']/div/div/div/form/div/div[1]/label/input")), firstName).perform();
        return this;
    }

    public AndersenRegPage enterLastName(String lastName) {
        actions.sendKeys(driver.findElement(By.xpath("//*[@id='root']/div/div/div/form/div/div[2]/label/input")), lastName).perform();
        return this;
    }

    public AndersenRegPage selectBirthYear(String year) {
        actions.click(driver.findElement(By.xpath("//*[@id='root']/div/div/div/form/div/label/div[1]/div/input"))).sendKeys(year, "-").build()
                .perform();
        return this;
    }

    public boolean isRegistrationSuccessful() {
        try {
            wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='root']/div/div/div/form/div/div/div/span")));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public AndersenRegPage selectBirthMonth(String month) {
        actions.click(driver.findElement(By.xpath("//*[@id='root']/div/div/div/form/div/label/div[1]/div/input"))).sendKeys(month, "-").build()
                .perform();
        return this;
    }

    public AndersenRegPage selectBirthDay(String day) {
        actions.click(driver.findElement(By.xpath("//*[@id='root']/div/div/div/form/div/label/div[1]/div/input"))).sendKeys(day, "-").build()
                .perform();
        return this;
    }


    public void submitRegistrationForm() {
        actions.click(driver.findElement(By.xpath("//*[@id='root']/div/div/div/form/button"))).perform();

    }

    public AndersenRegPage enterEmailAndPassword(String email, String password) {
        actions.click(driver.findElement(By.xpath("//*[@id='root']/div/header")))
                .sendKeys(driver.findElement(By.xpath("//*[@id='root']/div/div/div/form/div/div[3]/label/input")), email)
                .sendKeys(Keys.TAB, password)
                .sendKeys(Keys.TAB, password)
                .build()
                .perform();
        return this;
    }

    public boolean visibiliityPassErr() {
        try {
            wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='root']/div/div/div/form/div/div[4]/div/span")));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

}
