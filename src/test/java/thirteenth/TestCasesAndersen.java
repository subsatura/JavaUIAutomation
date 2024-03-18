package thirteenth;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tenth.DriverSetUp;

import java.time.Duration;

import static junit.framework.Assert.assertFalse;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Listeners({MyAllureListeners.class})
public class TestCasesAndersen {

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
    @Description("Login with incorrect data")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Login test")
    public void loginTestA03() {
        AndersenLoginPage loginPage = new AndersenLoginPage(driver);
        loginPage.goTo()
                .enterEmail("subsatura@gmail.com")
                .enterPassword("Sample@124")
                .submitLoginForm();
        boolean errorMessage = loginPage.getErrorMessage();
        System.out.println(errorMessage);
        assertTrue(errorMessage);
    }

    @Test
    @Description("Test redirection from login to the main page if logged in")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Test redirection")
    public void testRedirectionA04() {
        AndersenLoginPage loginPage = new AndersenLoginPage(driver);
        loginPage.goTo()
                .enterEmail("subsatura@gmail.com")
                .enterPassword("21121488")
                .submitLoginForm();
        loginPage.goTo();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.urlToBe("https://qa-course-01.andersenlab.com/"));
        String currentUrl = driver.getCurrentUrl();
        assertEquals(currentUrl, "https://qa-course-01.andersenlab.com/");
    }

    @Test
    @Description("Test redirection from registration to the main page if logged in")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Test redirection")
    public void testRedirectionA05() {
        AndersenLoginPage loginPage = new AndersenLoginPage(driver);
        loginPage.goTo()
                .enterEmail("subsatura@gmail.com")
                .enterPassword("21121488")
                .submitLoginForm();
        loginPage.goToReg();
        String currentUrl = loginPage.returnUrl();
        assertEquals(currentUrl, "https://qa-course-01.andersenlab.com/");
    }

    @Test
    @Description("Test input validation for First Name and Last Name fields")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Error displaying")
    public void testInputValidationB04() {
        AndersenRegPage andersenRegPage = new AndersenRegPage(driver);
        andersenRegPage.goTo()
                .enterFirstName("Sam546")
                .enterLastName("Meller3456")
                .selectBirthYear("1990")
                .selectBirthMonth("08")
                .selectBirthDay("10")
                .enterEmailAndPassword("sam@sam.samm", "password")
                .submitRegistrationForm();
        assertFalse(andersenRegPage.isRegistrationSuccessful());
    }

    @Test
    @Description("Test too short input validation for Password field")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Error displaying")
    public void testInputValidationB06() {
        AndersenRegPage andersenRegPage = new AndersenRegPage(driver);
        andersenRegPage.goTo()
                .enterFirstName("Sam")
                .enterLastName("Meller")
                .selectBirthYear("1990")
                .selectBirthMonth("08")
                .selectBirthDay("10")
                .enterEmailAndPassword("sam@sam.samm", "pass")
                .submitRegistrationForm();
        assertTrue(andersenRegPage.visibiliityPassErr());
    }

    @Test
    @Description("Test too long input validation for Password field")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Error displaying")
    public void testInputValidationB07() {
        AndersenRegPage andersenRegPage = new AndersenRegPage(driver);
        andersenRegPage.goTo()
                .enterFirstName("Sam")
                .enterLastName("Meller")
                .selectBirthYear("1990")
                .selectBirthMonth("08")
                .selectBirthDay("10")
                .enterEmailAndPassword("sam@sam.samm", "passpasspasspasspasspass")
                .submitRegistrationForm();
        assertTrue(andersenRegPage.visibiliityPassErr());
    }
}