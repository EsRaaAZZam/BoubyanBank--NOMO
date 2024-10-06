package com.Demo;

import com.Demo.driver.DriverSingleton;
import com.Demo.screens.Login;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DemoSauceApp {

    public Login login;

    public DemoSauceApp(AppiumDriver<MobileElement> driver) {
        this.login = new Login(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(30)), this);
    }


    public void waitForLoadingToFinish() {
        WebDriverWait wait = new WebDriverWait(DriverSingleton.getDriverSingleton().getDriver(), 35);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("com.emeint.android.myservices:id/progress_overlay")));
    }


}
