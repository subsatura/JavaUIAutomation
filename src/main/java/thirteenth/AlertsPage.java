package thirteenth;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class AlertsPage {
    private final WebDriver driver;
    private final Actions actions;

    public AlertsPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    public static String getNodeText(WebElement element) {
        return element.getAttribute("innerText").trim().replaceAll("\\n", "");
    }

    public void clickOnAlertBoxAndReturnOutputToConsole() {
        actions.click(driver.findElement(By.xpath("//*[@id='alertBox']"))).perform();
        driver.switchTo().alert().accept();
        System.out.println(getNodeText(driver.findElement(By.xpath("//*[@id='output']"))));
    }

    public void clickOnPromptBoxBoxAndReturnOutputToConsole() {
        actions.click(driver.findElement(By.xpath("//*[@id='promptBox']"))).perform();
        Alert promptAlert = driver.switchTo().alert();
        promptAlert.sendKeys("Final step of this task");
        promptAlert.accept();
        System.out.println(getNodeText(driver.findElement(By.xpath("//*[@id='output']"))));
    }

    public void clickOnConfirmBoxBoxAndReturnOutputToConsole() {
        actions.click(driver.findElement(By.xpath("//*[@id='confirmBox']"))).perform();
        driver.switchTo().alert().dismiss();
        System.out.println(getNodeText(driver.findElement(By.xpath("//*[@id='output']"))));
    }


}
