package com.Demo.base_test;

import com.Demo.device.Device;
import com.Demo.server.appiumServer;

import java.util.Properties;

/**
 * The type Base test.
 */
public class BaseTest {
    ;
    /**
     * The constant users.
     */
    protected static Properties users;
    /**
     * The constant device.
     */
    protected static Device device = new Device(35);
    /**
     * The constant beforeAll.
     */
    protected static boolean beforeAll = true;
    /**
     * The Android platform.
     */
    public boolean androidPlatform = true;
    /**
     * The Server.
     */
    public appiumServer server = new appiumServer();

}
