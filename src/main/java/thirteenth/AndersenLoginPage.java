package thirteenth;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AndersenLoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public AndersenLoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    }

    public AndersenLoginPage goTo() {
        driver.get("https://qa-course-01.andersenlab.com/login");
        return this;
    }
    public void goToReg() {
        driver.get("https://qa-course-01.andersenlab.com/registration");
        wait.until(ExpectedConditions.urlContains("/registration"));
    }
    public String returnUrl(){
        try {wait.until(ExpectedConditions.urlToBe("https://qa-course-01.andersenlab.com/"));
        }
        catch (Exception ignored){}
        return driver.getCurrentUrl();
    }
    public AndersenLoginPage enterEmail(String email) {
        WebElement emailInput = driver.findElement(By.xpath("//input[@name='email']"));
        emailInput.sendKeys(email);
        return this;
    }

    public AndersenLoginPage enterPassword(String password) {
        WebElement passwordInput = driver.findElement(By.xpath("//input[@name='password']"));
        passwordInput.sendKeys(password);
        return this;
    }

    public void submitLoginForm() {
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        loginButton.click();
        try {
            wait.until(ExpectedConditions.urlToBe("https://qa-course-01.andersenlab.com/"));
        }
        catch (Exception ignored){
        }
        }

    public boolean getErrorMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='root']/div/div/div/form/div/div[1]/div/span")));
        return driver.findElement(By.xpath("//*[@id='root']/div/div/div/form/div/div[1]/div/span")).isDisplayed();
    }
}
