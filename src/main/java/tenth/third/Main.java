package tenth.third;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//Написать метод в параметры которого принимаются два webelement. Метод выводит в консоль информацию какой из двух элементов располагается
// выше на странице, какой из элементов располагается левее на странице, а также какой из элементов занимает большую площадь.
// Параметры метода могут также включать в себя другие аргументы, если это необходимо.
public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.setBinary("C:\\chrome-win64\\chrome.exe");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("http://www.automationpractice.pl/index.php");
        WebElement element1 = driver.findElement(By.xpath("//*[@id='htmlcontent_top']/ul/li[2]/a/img"));
        WebElement element2 = driver.findElement(By.xpath("//*[@id='htmlcontent_home']/ul/li[2]/a/img"));
        WebElementComparator comparator = new WebElementComparator();
        comparator.compareElements(element1, element2); // Вызываем метод compareElements для сравнения элементов
        driver.quit();
    }
}
