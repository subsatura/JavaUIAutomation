package twelfth.task1;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tenth.DriverSetUp;

import java.time.Duration;

import static junit.framework.Assert.assertTrue;

public class TestFirst {
    static WebDriver driver;
    static WebDriverWait wait;
    static Actions actions;

    public static boolean isScrollAtTop(WebDriver driver) {        // Получаем текущую позицию скролла по вертикали с помощью JavaScript
        Long scrollPosition = (Long) ((JavascriptExecutor) driver).executeScript("return window.pageYOffset;");
        return scrollPosition == 0;        // Если позиция скролла равна 0, значит мы находимся в самом верху страницы и возвращаем положительное значение
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
    public void visibilityMenu() { // Проверка видимости котекстного меню кнопки Tech Stack при наведении мыши
        driver.get("https://andersenlab.com/"); //Получаем адрес
        By cookieButton = By.xpath("//*[@id='gatsby-focus-wrapper']/div/section/div/button"); //находим и подтверждаем куки
        wait.until(ExpectedConditions.visibilityOfElementLocated(cookieButton));
        driver.findElement(cookieButton).click();
        By button = By.xpath("//*[@id='gatsby-focus-wrapper']/header/div/div[1]/nav/div[1]/div/div[1]/a"); //Локатор для кнопки меню Tech Stack
        actions.moveToElement(driver.findElement(button)).build().perform(); // Действия для наведения мыши на кнопку
        By element = By.xpath("//*[@id='gatsby-focus-wrapper']/header/div/div[1]/nav/div[1]/div/div[1]/div"); //Локатор контекстного меню
        boolean isElementVisible = false;
        try {
            isElementVisible = wait.until(ExpectedConditions.visibilityOf(driver.findElement(element))) != null; //Возврат положительного значения при видимости
        } catch (TimeoutException ignored) {
        }
        assertTrue(isElementVisible);
    }

    @Test
    public void checkTopScroller() throws InterruptedException { //Тест работы кнопки TopScroller которая возвращает в начало страницы
        driver.get("https://andersenlab.com/");
        By cookieButton = By.xpath("//*[@id='gatsby-focus-wrapper']/div/section/div/button");
        wait.until(ExpectedConditions.visibilityOfElementLocated(cookieButton));
        driver.findElement(cookieButton).click();
        By topScroller = By.xpath("//*[@id='gatsby-focus-wrapper']/footer/section/div/section[3]/div/button"); //Локатор кнопки
        actions.scrollToElement(driver.findElement(topScroller)).moveToElement(driver.findElement(topScroller)).click().build().perform();// Создаем действие для скролла к кнопки, наведению на нее мыши и нажатию на нее
        Thread.sleep(1000); //Секунда на прокрутку страницы, если дольше, значит кнопка не работает или работает слишком плавно
        assertTrue(isScrollAtTop(driver));
    }
}

