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
@Listeners({MyAllureListeners.class})

public class TestSecond {
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
    @Description("Three pages task")
    @Severity(SeverityLevel.NORMAL)
    @Feature("WebDriver")
    public void mainTest() {
        FirstTab firstTab = new FirstTab(driver);
        firstTab.goTo("https://www.google.com/search").searchFor("https://www.guinnessworldrecords.com/account/register?").getFirstSearchResultUrlInNewTab();
        firstTab.searchFor("https://www.hyrtutorials.com/p/alertsdemo.html").getFirstSearchResultUrlInNewTab();
        firstTab.goTo("https://www.w3schools.com/html/tryit.asp?filename=tryhtml_form_submit").fillFormClickSumbitReturnMessageToConsole("Ivan", "Ivanov");
        firstTab.switchToNextTab();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.fillRegistrationForm("Ivan", "Ivanov", "26", "05", "1999", "Hungary", "Budapest", "email@email.email", "password");
        registrationPage.switchToNextTab();
        AlertsPage alertsPage = new AlertsPage(driver);
        alertsPage.clickOnAlertBoxAndReturnOutputToConsole();
        alertsPage.clickOnConfirmBoxBoxAndReturnOutputToConsole();
        alertsPage.clickOnPromptBoxBoxAndReturnOutputToConsole();

    }
}
