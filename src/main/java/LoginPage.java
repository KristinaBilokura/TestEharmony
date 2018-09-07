import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {
    @FindBy(xpath = "//a[@href='/login']")
    private WebElement logInButton;
    @FindBy(xpath = "//input[@name='j_username']")
    private WebElement loginInput;
    @FindBy(xpath = "//input[@name='j_password']")
    private WebElement passwordInput;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;
    @FindBy(xpath = "//section[@class='errors']//h2")
    private WebElement noMessageImage;


    public LoginPage() { getPage("https://www-r3.eharmony.com/");
    }

    public LoginPage login(String email ,String password) {
        click(logInButton);
        sendKeysClickable(loginInput, email);
        sendKeysClickable(passwordInput, password);
        click(submitButton);
        return this;
    }

    public Boolean verifyIfMessageIsDisplayed(){
        return isElementDisplayed(noMessageImage);

    }

}
