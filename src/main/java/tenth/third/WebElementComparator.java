package tenth.third;

import org.openqa.selenium.WebElement;

public class WebElementComparator {

    public void compareElements(WebElement element1, WebElement element2) {
        int element1Top = element1.getLocation().getY();        // Получаем координаты верхней точки каждого элемента
        int element2Top = element2.getLocation().getY();

        int element1Left = element1.getLocation().getX();       // Получаем координаты левой точки каждого элемента
        int element2Left = element2.getLocation().getX();

        int element1Area = element1.getSize().getHeight() * element1.getSize().getWidth(); // Получаем площади каждого элемента
        int element2Area = element2.getSize().getHeight() * element2.getSize().getWidth();

        String aboveElement = (element1Top < element2Top) ? "Первый элемент" : "Второй элемент";// Определяем, какой элемент располагается выше на странице

        String leftElement = (element1Left < element2Left) ? "Первый элемент" : "Второй элемент";// Определяем, какой элемент располагается левее на странице

        String largerElement = (element1Area > element2Area) ? "Первый элемент" : "Второй элемент";// Определяем, какой элемент занимает большую площадь

        System.out.println("Элемент, расположенный выше на странице: " + aboveElement);
        System.out.println("Элемент, расположенный левее на странице: " + leftElement);
        System.out.println("Элемент, занимающий большую площадь: " + largerElement);
    }
}
