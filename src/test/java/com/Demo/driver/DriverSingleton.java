package com.Demo.driver;

import com.Demo.base_test.BaseTest;
import com.Demo.utilities.ConfigHandler;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * The type Driver singleton.
 */
public class DriverSingleton extends BaseTest {
    /**
     * The constant PLATFORM_VERSION.
     */
    public static final String PLATFORM_VERSION = "platformVersion";
    /**
     * The constant DEVICE_NAME.
     */
    public static final String DEVICE_NAME = "deviceName";
    /**
     * The constant UDID.
     */
    public static final String UDID = "udid";
    /**
     * The constant AUTOMATION_NAME.
     */
    public static final String AUTOMATION_NAME = "automationName";
    private static final String PLATFORM_NAME = "platformName";
    private static Properties properties;
    private static DesiredCapabilities capabilities;
    private static DriverSingleton driverSingleton = null;
    private AppiumDriver<MobileElement> driver;

    private DriverSingleton() {
        if (androidPlatform)
            setAndroidDriver();
        else
            setIosDriver();
    }

    /**
     * Gets driver singleton.
     *
     * @return the driver singleton
     */
    public static DriverSingleton getDriverSingleton() {
        if (driverSingleton == null)
            driverSingleton = new DriverSingleton();
        return driverSingleton;
    }

    private static void setIosDesiredCapabilities() throws IOException {
        String appDirectory = getAppDirectory();

        capabilities = new DesiredCapabilities();
        capabilities.setCapability(PLATFORM_NAME, properties.getProperty(PLATFORM_NAME));
        capabilities.setCapability(PLATFORM_VERSION, properties.getProperty(PLATFORM_VERSION));
        capabilities.setCapability(DEVICE_NAME, properties.getProperty(DEVICE_NAME));
        capabilities.setCapability("newCommandTimeout", 1200);
        capabilities.setCapability("app", appDirectory);
        capabilities.setCapability(UDID, properties.getProperty(UDID));
        capabilities.setCapability(AUTOMATION_NAME, "uiAutomator2");
        capabilities.setCapability("autoDismissAlerts", true);
        capabilities.setCapability("locale", "EG");
        capabilities.setCapability("unicodeKeyboard", true);
        capabilities.setCapability("resetKeyboard", true);
    }

    private static void setAppPackageAndActivity() {
        capabilities.setCapability("appPackage", properties.getProperty("appPackage"));
        capabilities.setCapability("appActivity", properties.getProperty("appActivity"));
    }

    private static String getAppDirectory() throws IOException {
        File app = new File(properties.getProperty("app"));
        String appDirectory = app.getCanonicalPath();
        return appDirectory;
    }

    /**
     * Gets driver.
     *
     * @return the driver
     */
    public AppiumDriver<MobileElement> getDriver() {
        return driver;
    }

    /**
     * Sets android driver.
     */
    public void setAndroidDriver() {
        try {
            ConfigHandler.setAndroidProperties();
            properties = ConfigHandler.getAndroidProperties();
            setIosDesiredCapabilities();

            setAppPackageAndActivity();

            driver = new AndroidDriver(server.getCurrentServerUrl(), capabilities);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Sets ios driver.
     */
    public void setIosDriver() {
        try {
            ConfigHandler.setIOSProperties();
            properties = ConfigHandler.getIOSProperties();
            setIosDesiredCapabilities();

            driver = new IOSDriver(server.getCurrentServerUrl(), capabilities);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
