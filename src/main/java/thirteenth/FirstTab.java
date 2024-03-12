package thirteenth;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class FirstTab {
    private final WebDriver driver;
    private final Actions actions;
    private final WebDriverWait wait;
    public static String getNodeText(WebElement element) {
        return element.getAttribute("innerText").trim().replaceAll("\\n", "");
    }
    public FirstTab(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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

    public FirstTab goTo(String url) {
        driver.get(url);
        return this;
    }

    public FirstTab searchFor(String query) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='APjFqb']")));

        WebElement searchInput = driver.findElement(By.xpath("//*[@id='APjFqb']"));
        actions.click(searchInput).
                keyDown(Keys.LEFT_CONTROL)
                .sendKeys("a")
                .keyUp(Keys.LEFT_CONTROL)
                .sendKeys(Keys.BACK_SPACE)
                .sendKeys(searchInput, query, Keys.ENTER)
                .build()
                .perform();
        return this;
    }
    public void fillFormClickSumbitReturnMessageToConsole(String firstName, String lastName){
        driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='iframeResult']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fname")));
        actions.click(driver.findElement(By.id("fname")))
                .keyDown(Keys.LEFT_CONTROL)
                .sendKeys("a")
                .keyUp(Keys.LEFT_CONTROL)
                .sendKeys(Keys.BACK_SPACE, firstName, Keys.TAB)
                .keyDown(Keys.LEFT_CONTROL)
                .sendKeys("a")
                .keyUp(Keys.LEFT_CONTROL)
                .sendKeys(lastName)
                .click(driver.findElement(By.xpath("/html/body/form/input[3]")))
                .build()
                .perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/p")));
        System.out.println(getNodeText(driver.findElement(By.xpath("/html/body/div[2]/p"))));
    }

    public void getFirstSearchResultUrlInNewTab() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='search']//a")));
        actions.keyDown(Keys.LEFT_CONTROL)
                .click(driver.findElement(By.xpath("//div[@id='search']//a")))
                .keyUp(Keys.LEFT_CONTROL)
                .build()
                .perform();
    }
}
