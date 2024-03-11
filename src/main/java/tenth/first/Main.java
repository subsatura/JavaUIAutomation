package tenth.first;
// 1)Автоматизируйте по два тест-кейса из каждого модуля, которые вы писали для предыдущего домашнего задания.

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.setBinary("C:\\chrome-win64\\chrome.exe");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        driver.get("https://qa-course-01.andersenlab.com/login"); //A01 testcase редирект на регистрацию
        driver.findElement(By.linkText("Registration")).click();

        driver.switchTo().newWindow(WindowType.TAB);

        driver.get("https://qa-course-01.andersenlab.com/login"); //A02 testcase ввод логина пароля и клик на вход
        driver.findElement(By.name("email")).sendKeys("subsatura@gmail.com");
        driver.findElement(By.name("password")).sendKeys("21121488");
        driver.findElement(By.xpath("//*[text()='Sign in']")).click();
        Thread.sleep(300);

        driver.findElement(By.cssSelector(".text-sm")).click(); //A08 testcase логаут
        Thread.sleep(300);
        driver.findElement(By.xpath("//*[@id='react-confirm-alert']/div/div/div/div/button[1]")).click();

        driver.switchTo().newWindow(WindowType.TAB);

        driver.get("https://qa-course-01.andersenlab.com/registration"); //B01 testcase регситрация, ввод данных и клик на отправить
        driver.findElement(By.name("firstName")).sendKeys("Ivan");
        driver.findElement(By.name("lastName")).sendKeys("Ivanov");
        driver.findElement(By.name("dateOfBirth")).sendKeys("02/28/2024");
        driver.findElement(By.name("email")).click(); // клик чтобы убрать окошко даты
        driver.findElement(By.name("email")).sendKeys("345132432@gmail.com");
        driver.findElement(By.name("password")).sendKeys("21121488");
        driver.findElement(By.name("passwordConfirmation")).sendKeys("21121488");
        driver.findElement(By.cssSelector("button.rounded-3xl")).click();

        driver.switchTo().newWindow(WindowType.TAB);

        driver.get("https://qa-course-01.andersenlab.com/registration"); //B02 testcase регситрация, тест на пустые поля
        driver.findElement(By.name("firstName")).click();
        driver.findElement(By.name("lastName")).click();
        driver.findElement(By.name("dateOfBirth")).click();
        driver.findElement(By.name("email")).click(); // клик чтобы убрать окошко даты
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("passwordConfirmation")).click();
        driver.findElement(By.name("password")).click();
        WebElement errorFirstName = driver.findElement(By.xpath("//*[@id='root']/div/div/div/form/div/div[1]/div/span"));
        WebElement errorLastName = driver.findElement(By.xpath("//*[@id='root']/div/div/div/form/div/div[2]/div/span"));
        WebElement errorDateOfBirth = driver.findElement(By.xpath("//*[@id='root']/div/div/div/form/div/div[2]/div/span"));
        WebElement errorEmail = driver.findElement(By.xpath("//*[@id='root']/div/div/div/form/div/div[3]/div/span"));
        WebElement errorPass = driver.findElement(By.xpath("//*[@id='root']/div/div/div/form/div/div[4]/div/span"));
        WebElement errorPassConf = driver.findElement(By.xpath("//*[@id='root']/div/div/div/form/div/div[4]/div/span"));
        if (errorFirstName.isDisplayed() & errorLastName.isDisplayed() & errorDateOfBirth.isDisplayed() & errorEmail.isDisplayed() & errorPass.isDisplayed() & errorPassConf.isDisplayed()) {
            System.out.println("passed");
        } else {
            System.out.println("failed");
        }

        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://qa-course-01.andersenlab.com/registration"); //B03 testcase valid email
        driver.findElement(By.name("email")).sendKeys("345132432gmail.com");
        driver.findElement(By.name("lastName")).click();
        WebElement errorEmailFiled = driver.findElement(By.xpath("//*[@id='root']/div/div/div/form/div/div[3]/div/span"));
        if (errorEmailFiled.isDisplayed()) {
            System.out.println("passed");
        } else {
            System.out.println("failed");
        }

        driver.close();

        driver.quit();

    }
}
