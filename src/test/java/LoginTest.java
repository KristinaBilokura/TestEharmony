import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends DriverObject {

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() throws IOException {
        loadConfigProp("capabilities.properties");
        getDriver();
    }

    @BeforeMethod
    public void setUp() {
        getDriver();
    }

    @Test
    public void test() {
        LoginPage loginPage = new LoginPage();
        loginPage.
                login("tests@gmail.com","test")
                .verifyIfMessageIsDisplayed();
    }

    @AfterMethod
    public void closeDr() {
        releaseThread();
    }
}
