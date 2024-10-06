package com.Demo.utilities.extent_report;

import com.Demo.driver.DriverSingleton;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ScreenShot {

    private static final String imageEncode = "data:image/png;base64,";
    private static DriverSingleton driverSingleton = DriverSingleton.getDriverSingleton();

    public static String getScreenShot() {

        String base64Screenshot = imageEncode + takeScreenShot();
        return base64Screenshot;
    }

    private static String takeScreenShot() {
        return ((TakesScreenshot) driverSingleton.getDriver()).getScreenshotAs(OutputType.BASE64);
    }

}
