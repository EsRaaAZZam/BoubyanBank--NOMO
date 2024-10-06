package com.Demo.tests;

import com.Demo.base_test.BaseTest;
import com.Demo.driver.DriverSingleton;
import com.Demo.utilities.ConfigHandler;
import com.Demo.utilities.extent_report.ExtentReport;
import com.Demo.utilities.extent_report.ScreenShot;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.io.IOException;

/**
 * The type Hooks handler.
 */
public class HooksHandler extends BaseTest {

    /**
     * Before all.
     *
     * @throws IOException the io exception
     */
    @Before(order = 0)
    public void beforeAll() throws IOException {
        if (beforeAll) {
            ConfigHandler.setConfigProperties();
            users = ConfigHandler.getVUsersProperties();
            beforeAll = false;
        }
    }

    /**
     * Sets up.
     */
    @Before(order = 3)
    public void setUp() {
        if (androidPlatform)
            setupAndroid();
        else
            setupIos();

    }

    /**
     * Sets ios.
     */
    public void setupIos() {
        device.launchAppIOS();

    }

    /**
     * Sets android.
     */
    public void setupAndroid() {
        device.launchApp();

    }

    /**
     * Start tc hooks.
     *
     * @param scenario the scenario
     */
    @Before(order = 1)
    public void startTCHooks(Scenario scenario) {
        ExtentReport.setScenario(scenario);
        ExtentReport.startTC();
    }

    /**
     * Sets step defs.
     *
     * @throws NoSuchFieldException   the no such field exception
     * @throws IllegalAccessException the illegal access exception
     */
    @Before(order = 2)
    public void setStepDefs() throws NoSuchFieldException, IllegalAccessException {
        ExtentReport.setStepDefs();
    }


    /**
     * End tc.
     */
    @After(order = 1)
    public void endTC() {
        if (ExtentReport.isCurrentlyUsingReport()) {
            ExtentReport.getExtent().flush();
        }
    }

    /**
     * Tear down.
     */
    @After(order = 0)
    public void tearDown() {
        device.closeApp();
        //device.resetApp();
        server.closeAppiumServer();
    }

    /**
     * Close message app in background.
     */
    @After("@Close-MessagesAppIn-Background")
    public void closeMessageAppInBackground() {
        device.terminateApp("messageAppPackage");
    }

    /**
     * After step.
     *
     * @param scenario the scenario
     */
    @AfterStep
    public void AfterStep(Scenario scenario) {
        String stepName = ExtentReport.getCurrentStepName();
        Status logStatus;

        if (scenario.isFailed()) {
            logStatus = Status.FAIL;

            // Check if the driver is still active before taking a screenshot
            if (DriverSingleton.getDriverSingleton().getDriver() != null) {
                String base64Screenshot = ScreenShot.getScreenShot();
                ExtentReport.getTest().log(logStatus, stepName, MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
            } else {
                ExtentReport.getTest().log(logStatus, stepName + " (Driver session is not active)");
            }
        } else {
            logStatus = Status.PASS;
            ExtentReport.getTest().log(logStatus, stepName);
        }
    }


}


