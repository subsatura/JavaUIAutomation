package thirteenth;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import static thirteenth.FirstTab.getNodeText;


public class RegistrationPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions actions;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }


    public void fillRegistrationForm(String firstName, String lastName, String dayOfBirth, String mouthOfBirth, String yearOfBirth, String country, String city, String email, String password) {
        actions.click(driver.findElement(By.id("LastName")))
                .sendKeys(firstName, Keys.TAB, lastName, Keys.TAB, dayOfBirth, Keys.TAB, mouthOfBirth, Keys.TAB, yearOfBirth)
                .build()
                .perform();
        Select countries = new Select(driver.findElement(By.id("Country")));
        countries.selectByVisibleText(country);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='State']/option[1]")));
        actions.click(driver.findElement(By.xpath("//*[@id='State']")))
                .sendKeys(city, Keys.TAB, email, Keys.TAB, email, Keys.TAB, password, Keys.TAB, password + "1", Keys.ENTER)
                .build()
                .perform();
        System.out.println(getNodeText(driver.findElement(By.xpath("//*[@id='main']/div/div/div/div/div/div/form/section[2]/div[2]/div[2]/span"))));
    }


    public void switchToNextTab() {
        Set<String> windowHandles = driver.getWindowHandles();
        Iterator<String> iterator = windowHandles.iterator();
        String currentHandle = driver.getWindowHandle();

        while (iterator.hasNext()) {
            if (iterator.next().equals(currentHandle)) {
                if (iterator.hasNext()) {
                    driver.switchTo().window(iterator.next());
                    break;
                }
            }
        }
    }
}