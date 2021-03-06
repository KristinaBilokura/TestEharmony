import io.appium.java_client.TouchAction;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPage extends DriverObject {
    Actions actions;

    protected AbstractPage() {
        actions = new Actions(getDriver());
        PageFactory.initElements(getDriver(), this);
    }

    private WebDriverWait getWait() {
        return new WebDriverWait(getDriver(), 40,
                100);
    }

    protected WebElement waitUntilBeClickable(WebElement webElement) {
        return getWait().until(ExpectedConditions.elementToBeClickable(webElement));
    }

    protected WebElement waitUntilBeVisible(WebElement webElement) {
        return getWait().until(ExpectedConditions.visibilityOf(webElement));
    }

    protected Boolean isElementDisplayed(WebElement webElement) {
        return webElement.isDisplayed();
    }

    protected void sendKeysClickable(WebElement webElement, String text) {
        waitUntilBeClickable(webElement);
        webElement.clear();
        webElement.sendKeys(text);
    }

    protected void click(WebElement webElement) {
        waitUntilBeVisible(webElement);
        actions.moveToElement(webElement).perform();
        waitUntilBeClickable(webElement).click();
    }

    protected void clickTouch(WebElement webElement) {
        waitUntilBeVisible(webElement);
        TouchAction action = new TouchAction(getDriver());
        action.tap(webElement).perform();
    }

    protected void clickJs(WebElement webElement) {
        waitUntilBeVisible(webElement);
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].click();", webElement);
    }

    public void getPage(final String url) {
        getDriver().get(url);
    }

}
