package com.Demo.tests.login;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/com//Demo/tests/login",
        glue = {"com.Demo.tests"},
        plugin = {"html:reports/Login-Report.html"},
        monochrome = true
)
public class LoginRunner extends AbstractTestNGCucumberTests {
}
