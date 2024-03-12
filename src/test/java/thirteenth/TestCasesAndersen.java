package thirteenth;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tenth.DriverSetUp;

import static junit.framework.Assert.assertFalse;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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
    public void loginTestA03() { //тест на попытку логина с неверными данными
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
    public void testRedirectionA04() { //Test redirection to the main page if logged in
        AndersenLoginPage loginPage = new AndersenLoginPage(driver);
        loginPage.goTo()
                .enterEmail("subsatura@gmail.com")
                .enterPassword("21121488")
                .submitLoginForm();
        loginPage.goTo();
        String currentUrl = driver.getCurrentUrl();
        assertEquals(currentUrl, "https://qa-course-01.andersenlab.com/");
    }

    @Test
    public void testRedirectionA05() {//Test redirection to the main page if logged in
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
    public void testInputValidationB04() { //Test input validation for First Name and Last Name fields
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
    public void testInputValidationB06() { //Test too short input validation for Password field
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
    public void testInputValidationB07() { //Test too long input validation for Password field
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