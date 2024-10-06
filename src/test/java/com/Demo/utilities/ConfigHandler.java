package com.Demo.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * The type Config handler.
 */
public class ConfigHandler {

    /**
     * The constant ANDROID_CONFIG_PATH.
     */
    public static final String ANDROID_CONFIG_PATH = "resources/config/config-android.properties";
    /**
     * The constant VF_CASH_CONFIG_PATH.
     */
    public static final String VF_CASH_CONFIG_PATH = "resources/config/config-vfCash.properties";
    /**
     * The constant Users_CONFIG_PATH.
     */
    public static final String Users_CONFIG_PATH = "resources/config/users.properties";
    /**
     * The constant CREDIT_CARDS_CONFIG_PATH.
     */
    public static final String CREDIT_CARDS_CONFIG_PATH = "resources/config/config-credit-cards.properties";
    /**
     * The constant SCRATCH_CARDS_CONFIG_PATH.
     */
    public static final String SCRATCH_CARDS_CONFIG_PATH = "resources/config/config-scratch-cards.properties";
    /**
     * The constant RATE_PLANES_CONFIG_PATH.
     */
    public static final String RATE_PLANES_CONFIG_PATH = "resources/config/config-rateplan.properties";
    private static final String IOS_CONFIG_PATH = "resources/config/config-ios.properties";
    private static Properties androidProperties;
    private static Properties vfCashProperties;
    private static Properties creditCardsProperties;
    private static Properties scratchCardsProperties;
    private static Properties ratePlanesProperties;
    private static Properties iOSProperties;
    private static Properties userProperties;

    /**
     * Sets config properties.
     *
     * @throws IOException the io exception
     */
    public static void setConfigProperties() throws IOException {
        setVUsershProperties();
    }

    /**
     * Sets ios properties.
     *
     * @throws IOException the io exception
     */
    public static void setIOSProperties() throws IOException {
        iOSProperties = new Properties();
        iOSProperties.load(new FileInputStream(IOS_CONFIG_PATH));
    }

    /**
     * Sets android properties.
     *
     * @throws IOException the io exception
     */
    public static void setAndroidProperties() throws IOException {
        androidProperties = new Properties();
        androidProperties.load(new FileInputStream(ANDROID_CONFIG_PATH));
    }



    /**
     * Sets v usersh properties.
     *
     * @throws IOException the io exception
     */
    public static void setVUsershProperties() throws IOException {
        userProperties = new Properties();
        userProperties.load(new FileInputStream(Users_CONFIG_PATH));
    }



    /**
     * Gets android properties.
     *
     * @return the android properties
     */
    public static Properties getAndroidProperties() {
        return androidProperties;
    }


    /**
     * Gets v users properties.
     *
     * @return the v users properties
     */
    public static Properties getVUsersProperties() {
        return userProperties;
    }



    /**
     * Gets ios properties.
     *
     * @return the ios properties
     */
    public static Properties getIOSProperties() {
        return iOSProperties;
    }




    /**
     * Update credit cards config.
     *
     * @throws IOException the io exception
     */
    public static void updateCreditCardsConfig() throws IOException {
        creditCardsProperties.store(new FileOutputStream(CREDIT_CARDS_CONFIG_PATH), null);
    }


}
