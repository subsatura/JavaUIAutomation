package thirteenth;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AndersenHomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions actions;

    public AndersenHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }

    public AndersenHomePage goTo() { //Переход на  страницу
        driver.get("https://andersenlab.com/");
        acceptCookies();
        return this;
    }

    public void acceptCookies() { //Тык на куки
        By cookieButton = By.xpath("//*[@id='gatsby-focus-wrapper']/div/section/div/button");
        wait.until(ExpectedConditions.visibilityOfElementLocated(cookieButton));
        driver.findElement(cookieButton).click();
    }

    public AndersenHomePage hoverOverTechStackButton() {//Наведение на первую кнопку
        By button = By.xpath("//*[@id='gatsby-focus-wrapper']/header/div/div[1]/nav/div[1]/div/div[1]/a");
        wait.until(ExpectedConditions.visibilityOfElementLocated(button));
        actions.moveToElement(driver.findElement(button)).perform();
        return this;
    }

    public boolean isTechStackMenuVisible() { //Видимость меню первой кнопки
        By element = By.xpath("//*[@id='gatsby-focus-wrapper']/header/div/div[1]/nav/div[1]/div/div[1]/div");
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(element)) != null;
        } catch (TimeoutException ignored) {
            return false;
        }
    }

    public AndersenHomePage clickTopScroller() { //Переход на кнопку котрая скролит страницу наверх
        By topScroller = By.xpath("//*[@id='gatsby-focus-wrapper']/footer/section/div/section[3]/div/button");
        actions.moveToElement(driver.findElement(topScroller)).click().build().perform();
        wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.xpath("//*[@id='gatsby-focus-wrapper']/header/div/div[1]")))); // ждем прокрутки до конца
        return this;
    }

    public boolean isScrollAtTop() { //Проверка нахождения бегунка прокрутки вначале страницы
        Long scrollPosition = (Long) ((JavascriptExecutor) driver).executeScript("return window.pageYOffset;");
        return scrollPosition == 0;
    }
}
