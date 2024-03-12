package thirteenth;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tenth.DriverSetUp;

import static org.testng.Assert.assertTrue;

public class TestFirst {
    private WebDriver driver;
    private AndersenHomePage andersenHomePage;

    @BeforeMethod
    public void setUp() {
        driver = DriverSetUp.setUpDriver();
        andersenHomePage = new AndersenHomePage(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void visibilityMenu() {
        assertTrue(andersenHomePage.goTo().hoverOverTechStackButton().isTechStackMenuVisible());
    }

    @Test
    public void checkTopScroller() {
        assertTrue(andersenHomePage.goTo().clickTopScroller().isScrollAtTop());
    }
}
