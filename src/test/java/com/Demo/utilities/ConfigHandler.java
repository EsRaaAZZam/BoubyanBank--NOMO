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
     * Sets vf cash properties.
     *
     * @throws IOException the io exception
     */
    public static void setVfCashProperties() throws IOException {
        vfCashProperties = new Properties();
        vfCashProperties.load(new FileInputStream(VF_CASH_CONFIG_PATH));
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
     * Sets credit cards properties.
     *
     * @throws IOException the io exception
     */
    public static void setCreditCardsProperties() throws IOException {
        creditCardsProperties = new Properties();
        creditCardsProperties.load(new FileInputStream(CREDIT_CARDS_CONFIG_PATH));
    }

    /**
     * Sets scratch cards properties.
     *
     * @throws IOException the io exception
     */
    public static void setScratchCardsProperties() throws IOException {
        scratchCardsProperties = new Properties();
        scratchCardsProperties.load(new FileInputStream(SCRATCH_CARDS_CONFIG_PATH));
    }

    /**
     * Sets rate planes properties.
     *
     * @throws IOException the io exception
     */
    public static void setRatePlanesProperties() throws IOException {
        ratePlanesProperties = new Properties();
        ratePlanesProperties.load(new FileInputStream(RATE_PLANES_CONFIG_PATH));
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
     * Gets vf cash properties.
     *
     * @return the vf cash properties
     */
    public static Properties getVfCashProperties() {
        return vfCashProperties;
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
     * Gets credit cards properties.
     *
     * @return the credit cards properties
     */
    public static Properties getCreditCardsProperties() {
        return creditCardsProperties;
    }


    /**
     * Gets scratch cards properties.
     *
     * @return the scratch cards properties
     */
    public static Properties getScratchCardsProperties() {
        return scratchCardsProperties;
    }

    /**
     * Gets rate planes properties.
     *
     * @return the rate planes properties
     */
    public static Properties getRatePlanesProperties() {
        return ratePlanesProperties;
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
     * Update scratch cards config.
     *
     * @throws IOException the io exception
     */
    public static void updateScratchCardsConfig() throws IOException {
        scratchCardsProperties.store(new FileOutputStream(SCRATCH_CARDS_CONFIG_PATH), null);
    }

    /**
     * Update credit cards config.
     *
     * @throws IOException the io exception
     */
    public static void updateCreditCardsConfig() throws IOException {
        creditCardsProperties.store(new FileOutputStream(CREDIT_CARDS_CONFIG_PATH), null);
    }

    /**
     * Update vf cash config.
     *
     * @throws IOException the io exception
     */
    public static void updateVFCashConfig() throws IOException {
        vfCashProperties.store(new FileOutputStream(VF_CASH_CONFIG_PATH), null);
    }
}
