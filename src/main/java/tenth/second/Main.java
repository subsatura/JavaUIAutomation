package tenth.second;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//2)Написать программу, которая будет открывать пять различных страниц в новых окнах:
//http://www.automationpractice.pl/index.php
//https://zoo.waw.pl/
//https://www.w3schools.com/
//https://www.clickspeedtester.com/click-counter/
//https://andersenlab.com/
//        Прописать цикл, который будет переключаться поочередно через все страницы,
//для каждой страницы выводить в консоль название и ссылку на эту страницу.
//И будет закрывать ту страницу в названии которой есть слово "Zoo".
public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.setBinary("C:\\chrome-win64\\chrome.exe");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("http://www.automationpractice.pl/index.php");// Открываем первую страницу после инициализации дравера

        String[] urls = {
                "https://zoo.waw.pl/",
                "https://www.w3schools.com/",
                "https://www.clickspeedtester.com/click-counter/",
                "https://andersenlab.com/"
        };

        for (String url : urls) {         // Открываем каждую страницу в новой вкладке
            driver.switchTo().newWindow(WindowType.TAB);
            driver.get(url);
        }

        for (String handle : driver.getWindowHandles()) {         // Переключение между вкладками и вывод информации о каждой странице
            driver.switchTo().window(handle);
            String title = driver.getTitle();
            String currentUrl = driver.getCurrentUrl();
            System.out.println("Название страницы: " + title);
            System.out.println("Ссылка на страницу: " + currentUrl);

            if (title.contains("Zoo")) {             // Проверка наличия слова "Zoo" в названии и закрытие вкладки

                driver.close();
                System.out.println("Страница с названием 'Zoo' закрыта.");
            }
        }

        driver.quit();

    }
}