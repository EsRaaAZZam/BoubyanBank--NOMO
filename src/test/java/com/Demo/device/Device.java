package com.Demo.device;


import com.Demo.DemoSauceApp;
import com.Demo.driver.DriverSingleton;
import com.Demo.utilities.ConfigHandler;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;


/**
 * The type Device.
 */
public class Device {

    private final DriverSingleton driverSingleton = DriverSingleton.getDriverSingleton();

    /**
     * The Demo sauce app.
     */
    public DemoSauceApp demoSauceApp = new DemoSauceApp(driverSingleton.getDriver());


    /**
     * Instantiates a new Device.
     *
     * @param waitTime the wait time
     */
    public Device(int waitTime) {
        PageFactory.initElements(new AppiumFieldDecorator(driverSingleton.getDriver(), Duration.ofSeconds(waitTime)), this);
    }


    /**
     * Launch app.
     */
    public void launchApp() {
        driverSingleton.getDriver().activateApp(ConfigHandler.getAndroidProperties().getProperty("appPackage"));

    }
    /**
     * Launch app.
     */
    public void launchAppIOS() {
        driverSingleton.getDriver().activateApp(ConfigHandler.getIOSProperties().getProperty("appPackage"));

    }




    /**
     * Terminate app.
     *
     * @param appPackage the app package
     */
    public void terminateApp(String appPackage) {
        driverSingleton.getDriver().terminateApp(ConfigHandler.getAndroidProperties().getProperty(appPackage));
    }

    /**
     * Close app.
     */
    public void closeApp() {
        if (driverSingleton.getDriver() != null) {
            driverSingleton.getDriver().terminateApp(ConfigHandler.getAndroidProperties().getProperty("appPackage"));
        }
    }

    /**
     * Reset app.
     */
    public void resetApp() {
        driverSingleton.getDriver().removeApp(ConfigHandler.getAndroidProperties().getProperty("appPackage"));
    }


}
