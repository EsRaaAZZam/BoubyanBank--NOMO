package com.Demo.utilities.extent_report;

import com.Demo.base_test.BaseTest;
import com.Demo.utilities.ConfigHandler;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.core.backend.TestCaseState;
import io.cucumber.java.Scenario;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestCase;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class ExtentReport extends BaseTest {

    private static final String extendReportPath = System.getProperty("user.dir") + "/resources/reports/";
    private static final String delegateFieldName = "delegate";
    private static final String testCaseFieldName = "testCase";
    private final static ExtentReports extent = new ExtentReports();
    private static final String ADB_GET_APP_VERSION_COMMAND = "adb shell dumpsys package com.emeint.android.myservices | grep versionName";
    public static int tcNumber = 1;
    private static ExtentTest test;
    private static int currentStepDefIndex;
    private static Scenario scenario;
    private static List<PickleStepTestStep> stepDefs;
    private static boolean currentlyUsingReport = false;
    private static String fullAppVersionName;
    private final String APP_VERSION = "App Version ";
    private final String REPORT_NAME = "Report Name";
    private final String PLATFORM_NAME = "Platform Name";
    private final String PLATFORM_VERSION = "Platform Version";
    ExtentSparkReporter sparkReporter;
    private String reportName;
    private Properties androidProperties = ConfigHandler.getAndroidProperties();
    private Properties iosProperties = ConfigHandler.getIOSProperties();

    public static void setScenario(Scenario currentScenario) {
        scenario = currentScenario;
    }

    public static void startTC() {
        String testCaseName = tcNumber++ + "-  " + scenario.getName();
        test = extent.createTest(testCaseName);
        currentStepDefIndex = 0;
        test.assignCategory(getFeatureFileName());
    }

    public static void setStepDefs() throws NoSuchFieldException, IllegalAccessException {

        TestCase testCase = getTestCase();
        stepDefs = getTestStepsDefs(testCase);
    }

    private static TestCase getTestCase() throws NoSuchFieldException, IllegalAccessException {
        Field delegateField = scenario.getClass().getDeclaredField(delegateFieldName);
        delegateField.setAccessible(true);
        TestCaseState testCaseState = (TestCaseState) delegateField.get(scenario);

        Field testCaseField = testCaseState.getClass().getDeclaredField(testCaseFieldName);
        testCaseField.setAccessible(true);
        TestCase testCase = (TestCase) testCaseField.get(testCaseState);
        return testCase;
    }

    public static String getCurrentStepName() {
        String currentStepDescription;

        PickleStepTestStep currentStepDef = stepDefs.get(currentStepDefIndex);

        currentStepDescription = currentStepDef.getStep().getText();
        currentStepDefIndex += 1;

        return currentStepDescription;
    }

    private static List<PickleStepTestStep> getTestStepsDefs(TestCase testCase) {
        return testCase.getTestSteps()
                .stream()
                .filter(x -> x instanceof PickleStepTestStep)
                .map(x -> (PickleStepTestStep) x)
                .collect(Collectors.toList());
    }

    public static ExtentReports getExtent() {
        return extent;
    }

    public static ExtentTest getTest() {
        return test;
    }

    public static String getAppVersion() {
        try {
            Process adbProcess = Runtime.getRuntime().exec(ADB_GET_APP_VERSION_COMMAND);
            BufferedReader adbProcessReader = new BufferedReader(new InputStreamReader(adbProcess.getInputStream()));
            fullAppVersionName = adbProcessReader.readLine();
            adbProcessReader.close();
            adbProcess.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return splitAppVersionName(fullAppVersionName);
    }

    private static String splitAppVersionName(String fullAppVersionName) {
        String[] splitAppVersionName = fullAppVersionName.trim().split("=");
        return splitAppVersionName[1];
    }

    public static boolean isCurrentlyUsingReport() {
        return currentlyUsingReport;
    }

    public static String getFeatureFileName() {

        Path featureFilePath = Paths.get(scenario.getUri());
        String fileName = featureFilePath.getFileName().toString();
        String[] parts = fileName.split("\\.feature");
        return parts[0];
    }

    @BeforeTest
    @Parameters("ReportName")
    public void startReport(String reportName) throws IOException {
        currentlyUsingReport = true;
        initializeReportConfiguration(reportName);
        this.reportName = reportName;

    }

    @AfterTest
    public void endReport() {
        try {
            String filePath = "file:///" + extendReportPath + reportName + ".html";
            filePath = filePath.replaceAll(" ", "%20");
            filePath = filePath.replace("\\", "/");
            Desktop.getDesktop().browse(new URI(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void addReportNameToEnvironmentInfo(String reportName) {
        extent.setSystemInfo(REPORT_NAME, reportName);
    }

    private void addAppVersionToEnvironmentInfo() {
        extent.setSystemInfo(APP_VERSION, getAppVersion());
    }

    private void addPlatformNameToEnvironmentInfo() {
        if (androidPlatform)
            extent.setSystemInfo(PLATFORM_NAME, androidProperties.getProperty("platformName"));
        else
            extent.setSystemInfo(PLATFORM_NAME, iosProperties.getProperty("platformName"));
    }

    private void addPlatformVersionToEnvironmentInfo() {
        if (androidPlatform)
            extent.setSystemInfo(PLATFORM_VERSION, androidProperties.getProperty("platformVersion"));
        else
            extent.setSystemInfo(PLATFORM_VERSION, iosProperties.getProperty("platformVersion"));
    }

    private void initializeReportConfiguration(String reportName) throws IOException {
        String cssContent = "";
        String jsContent = "";

        sparkReporter = new ExtentSparkReporter(extendReportPath + reportName + ".html");
        sparkReporter.loadXMLConfig("src/test/com/Demo/utilities/extent_report/extent_styling_files/extent-config.xml");
        extent.attachReporter(sparkReporter);

        try {
            cssContent = new String(Files.readAllBytes(Paths.get("src/test/java/com/Demo/utilities/extent_report/extent_styling_files/extent.css")));
            jsContent = new String(Files.readAllBytes(Paths.get("src/test/java/com/Demo/utilities/extent_report/extent_styling_files/extent.js")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        sparkReporter.config().setCss(cssContent);
        sparkReporter.config().setJs(jsContent);

        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        addReportNameToEnvironmentInfo(reportName);
        if (androidPlatform) {
            addAppVersionToEnvironmentInfo();
        }
        addPlatformNameToEnvironmentInfo();
        addPlatformVersionToEnvironmentInfo();
    }
}