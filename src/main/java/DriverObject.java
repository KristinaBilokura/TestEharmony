import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DriverObject {

    private static Properties prop = new Properties();
    private static String APPIUM_PORT;
    private static String BROWSER_NAME;
    private static String PLATFORM_NAME;
    private static String PLATFORM_VERSION;
    private static String DEVICE_NAME;

    private static Map<Long, AppiumDriver> driverMap = new HashMap();


    public static void loadConfigProp(String propertyFileName) throws IOException {
        prop.load(ClassLoader.getSystemResource(propertyFileName).openStream());
        APPIUM_PORT = prop.getProperty("appium.server.port");
        DEVICE_NAME = prop.getProperty("device.name");
        BROWSER_NAME = prop.getProperty("browser.name");
        PLATFORM_NAME = prop.getProperty("platform.name");
        PLATFORM_VERSION = prop.getProperty("platform.version");
    }

    private static AppiumDriver createDriver() {
        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM_NAME);
        capability.setCapability(MobileCapabilityType.PLATFORM_VERSION, PLATFORM_VERSION);
        capability.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
        capability.setCapability(MobileCapabilityType.BROWSER_NAME, BROWSER_NAME);
        URL url = null;
        try {
            url = new URL("http://localhost:" + APPIUM_PORT + "/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        AppiumDriver driver = new AppiumDriver(url, capability);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return driver;
    }

    public static void releaseThread() {
        DriverObject.getDriver().quit();
        driverMap.remove(Thread.currentThread().getId());
    }

    public static AppiumDriver getDriver() {
        long currentThread = Thread.currentThread().getId();
        AppiumDriver driver = driverMap.get(currentThread);
        if (driver == null) {
            driver = createDriver();
            driverMap.put(currentThread, driver);
        }
        return driver;
    }
}