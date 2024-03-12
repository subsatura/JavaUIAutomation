package twelfth.task2;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tenth.DriverSetUp;

import java.time.Duration;

public class TestSecond {
    static WebDriver driver;
    static WebDriverWait wait;
    static Actions actions;

    public static String getNodeText(WebElement element) {
        return element.getAttribute("innerText").trim().replaceAll("\\n", "");
    }

    @BeforeMethod
    public void startDriver() {
        driver = DriverSetUp.setUpDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }

    @Test
    public void mainTest() {
        driver.get("https://www.google.com/search");
        By searchField = By.xpath("//*[@id='APjFqb']");
        WebElement searchInput = driver.findElement(searchField);
        actions.sendKeys(searchInput, "https://www.guinnessworldrecords.com/account/register?")
                .keyDown(Keys.ENTER)
                .build()
                .perform();
        String tab1 = driver.getWindowHandle();
        actions.keyDown(Keys.LEFT_CONTROL)
                .click(driver.findElement(By.xpath("//div[@id='search']//a")))
                .keyUp(Keys.LEFT_CONTROL)
                .build()
                .perform();
        String tab2 = null;
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(driver.getWindowHandle())) {
                tab2 = handle;
                break;
            }
        }
        actions.click(driver.findElement(searchField)).keyDown(Keys.LEFT_CONTROL)
                .sendKeys("a")
                .keyUp(Keys.LEFT_CONTROL)
                .sendKeys(Keys.BACK_SPACE, "https://www.hyrtutorials.com/p/alertsdemo.html", Keys.ENTER)
                .build()
                .perform();
        actions.keyDown(Keys.LEFT_CONTROL)
                .click(driver.findElement(By.xpath("//div[@id='search']//a")))
                .keyUp(Keys.LEFT_CONTROL)
                .build()
                .perform();
        String tab3 = null;
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(driver.getWindowHandle()) && !handle.equals(tab2)) {
                tab3 = handle;
                break;
            }
        }
        driver.switchTo().window(tab1);
        driver.get("https://www.w3schools.com/html/tryit.asp?filename=tryhtml_form_submit");
        driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='iframeResult']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fname")));
        actions.click(driver.findElement(By.id("fname")))
                .keyDown(Keys.LEFT_CONTROL)
                .sendKeys("a")
                .keyUp(Keys.LEFT_CONTROL)
                .sendKeys(Keys.BACK_SPACE, "Ivan", Keys.TAB)
                .keyDown(Keys.LEFT_CONTROL)
                .sendKeys("a")
                .keyUp(Keys.LEFT_CONTROL)
                .sendKeys("Ivanov")
                .click(driver.findElement(By.xpath("/html/body/form/input[3]")))
                .build()
                .perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/p")));
        System.out.println(getNodeText(driver.findElement(By.xpath("/html/body/div[2]/p"))));
        driver.switchTo().window(tab2);
        actions.click(driver.findElement(By.id("LastName")))
                .sendKeys("Ivan", Keys.TAB, "Ivanov", Keys.TAB, "26", Keys.TAB, "05", Keys.TAB, "1999")
                .build()
                .perform();
        Select countries = new Select(driver.findElement(By.id("Country")));
        countries.selectByValue("HU");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='State']/option[1]")));
        actions.click(driver.findElement(By.xpath("//*[@id='State']")))
                .sendKeys("Budapest", Keys.TAB, "emaiwl@amail.hwu", Keys.TAB, "emaiwl@amail.hwu", Keys.TAB, "Passward", Keys.TAB, "possworld", Keys.ENTER)
                .build()
                .perform();
        System.out.println(getNodeText(driver.findElement(By.xpath("//*[@id='main']/div/div/div/div/div/div/form/section[2]/div[2]/div[2]/span"))));
        driver.switchTo().window(tab3);
        actions.click(driver.findElement(By.xpath("//*[@id='alertBox']"))).perform();
        driver.switchTo().alert().accept();
        System.out.println(getNodeText(driver.findElement(By.xpath("//*[@id='output']"))));
        actions.click(driver.findElement(By.xpath("//*[@id='confirmBox']"))).perform();
        driver.switchTo().alert().dismiss();
        System.out.println(getNodeText(driver.findElement(By.xpath("//*[@id='output']"))));
        actions.click(driver.findElement(By.xpath("//*[@id='promptBox']"))).perform();
        Alert promptAlert = driver.switchTo().alert();
        promptAlert.sendKeys("Final step of this task");
        promptAlert.accept();
        System.out.println(getNodeText(driver.findElement(By.xpath("//*[@id='output']"))));
    }
}
