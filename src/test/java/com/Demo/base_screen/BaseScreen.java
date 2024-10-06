package com.Demo.base_screen;

import com.Demo.driver.DriverSingleton;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * The type Base screen.
 */
public class BaseScreen {
    private static WebDriverWait wait = new WebDriverWait(DriverSingleton.getDriverSingleton().getDriver(), 35);
    /**
     * The Driver.
     */
    protected AppiumDriver<MobileElement> driver;

    /**
     * Instantiates a new Base screen.
     *
     * @param driver the driver
     */
    public BaseScreen(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(30)), this);
    }

    /**
     * Gets element by partial text.
     *
     * @param text the text
     * @return the element by partial text
     */
// TODO: Remove STATIC & duplication
    public static MobileElement getElementByPartialText(String text) {
        String textXpath = "//*[contains(@text, \"" + text + "\")]";
        AppiumDriver<MobileElement> driver = DriverSingleton.getDriverSingleton().getDriver();
        WebDriverWait wait = new WebDriverWait(driver, 35);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(textXpath)));

        return driver.findElement(By.xpath(textXpath));
    }

    /**
     * Is text displayed boolean.
     *
     * @param text the text
     * @return the boolean
     */
    public static boolean isTextDisplayed(String text) {
        By xpathToText = By.xpath("//*[contains(@text, \"" + text + "\")]");

        waitForElementToBeVisible(xpathToText);

        return true;
    }

    /**
     * Wait for element to be visible.
     *
     * @param elementBy the element by
     */
    public static void waitForElementToBeVisible(By elementBy) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementBy));
    }

    /**
     * Check element existence by text boolean.
     *
     * @param text the text
     * @return the boolean
     */
    public boolean checkElementExistenceByText(String text) {
        WebDriverWait wait = new WebDriverWait(DriverSingleton.getDriverSingleton().getDriver(), 5);
        return DriverSingleton.getDriverSingleton().getDriver().findElements(By.xpath("//*[@text='" + text + "']")).size() >= 1;
    }

    /**
     * Check element existence by value boolean.
     *
     * @param text the text
     * @return the boolean
     */
    public boolean checkElementExistenceByValue(String text) {
        WebDriverWait wait = new WebDriverWait(DriverSingleton.getDriverSingleton().getDriver(), 5);
        return DriverSingleton.getDriverSingleton().getDriver().findElements(By.xpath("//*[@value='" + text + "']")).size() >= 1;
    }

    /**
     * Are elements vertically sorted boolean.
     *
     * @param firstElement  the first element
     * @param secondElement the second element
     * @return the boolean
     */
    public boolean areElementsVerticallySorted(MobileElement firstElement, MobileElement secondElement) {
        return (firstElement.getLocation().y < secondElement.getLocation().y);
    }


}
