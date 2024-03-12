package thirteenth;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tenth.DriverSetUp;

public class TestThird {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = DriverSetUp.setUpDriver();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void mainTest() {
        AndersenRegPage andersenRegPage = new AndersenRegPage(driver);
        andersenRegPage.goTo()
                .enterFirstName("Sam")
                .enterLastName("Meller")
                .selectBirthYear("1990")
                .selectBirthMonth("08")
                .selectBirthDay("10")
                .enterEmailAndPassword("sam@sam.sam", "password");
    }
}