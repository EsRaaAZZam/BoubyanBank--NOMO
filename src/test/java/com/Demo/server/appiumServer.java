package com.Demo.server;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.net.URL;

/**
 * The type Appium server.
 */
public class appiumServer {
    /**
     * The Service.
     */
    AppiumDriverLocalService service;

    /**
     * Instantiates a new Appium server.
     */
    public appiumServer() {

        AppiumServiceBuilder builder = new AppiumServiceBuilder().withArgument(() -> "--base-path", "/wd/hub");
        builder.withIPAddress("127.0.0.1");
        builder.usingAnyFreePort();

        service = AppiumDriverLocalService.buildService(builder);
        service.clearOutPutStreams();
        service.start();
        if (service == null || !service.isRunning()) {
            throw new RuntimeException("An appium server node is not started!");
        }
    }

    /**
     * Gets current server url.
     *
     * @return the current server url
     */
    public URL getCurrentServerUrl() {
        return service.getUrl();
    }

    /**
     * Close appium server.
     */
    public void closeAppiumServer() {
        if (service.isRunning()) {
            service.stop();
        }
    }

}
