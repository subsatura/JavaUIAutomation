package thirteenth;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tenth.DriverSetUp;

import static org.testng.Assert.assertTrue;
@Listeners({MyAllureListeners.class})


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
    @Description("Is menu visible when mouse on it")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Menu")
    public void visibilityMenu() {
        assertTrue(andersenHomePage.goTo().hoverOverTechStackButton().isTechStackMenuVisible());
    }

    @Test
    @Description("Check button for the top scrolling")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Top Scroller")
    public void checkTopScroller() {
        assertTrue(andersenHomePage.goTo().clickTopScroller().isScrollAtTop());
    }
}
