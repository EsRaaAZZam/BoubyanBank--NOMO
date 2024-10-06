package com.Demo.screens;

import com.Demo.base_screen.BaseScreen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;

/**
 * The type Login.
 */
public class Login extends BaseScreen {


    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='Username input field']")
    @iOSXCUITFindBy(accessibility = "Username input field")
    private MobileElement usernameInput;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Sorry, this user has been locked out.']")
    @iOSXCUITFindBy(accessibility = "Sorry, this user has been locked out.")
    private MobileElement lockedErrorMessage;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Provided credentials do not match any user in this service.']")
    @iOSXCUITFindBy(accessibility = "Provided credentials do not match any user in this service.")
    private MobileElement wrongErrorMessage;

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='Password input field']")
    @iOSXCUITFindBy(accessibility = "Password input field")
    private MobileElement passwordInput;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Login button']")
    @iOSXCUITFindBy(accessibility = "Login button")
    private MobileElement loginButton;

    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id='android:id/button1']")
    @iOSXCUITFindBy(accessibility = "Logout button")
    private MobileElement logoutButton;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Go Shopping button']")
    @iOSXCUITFindBy(accessibility = "Checkout")
    private MobileElement shoppingCartBtn;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='open menu']/android.widget.ImageView")
    @iOSXCUITFindBy(accessibility = "Open menu")
    private MobileElement sideMenu;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Log In']")
    @iOSXCUITFindBy(accessibility = "Log In")
    private MobileElement loginScreenInsideMenu;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='menu item log out']")
    @iOSXCUITFindBy(accessibility = "Log Out")
    private MobileElement logoutScreenInsideMenu;

    /**
     * Instantiates a new Login.
     *
     * @param driver the driver
     */
    public Login(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    /**
     * Gets locked error message.
     *
     * @return the locked error message
     */
    public MobileElement getLockedErrorMessage() {
        return lockedErrorMessage;
    }

    /**
     * Gets wrong error message.
     *
     * @return the wrong error message
     */
    public MobileElement getWrongErrorMessage() {
        return wrongErrorMessage;
    }

    /**
     * Gets side menu.
     *
     * @return the side menu
     */
    public MobileElement getSideMenu() {
        return sideMenu;
    }

    /**
     * Enter username.
     *
     * @param username the username
     */
    public void enterUsername(String username) {
        usernameInput.sendKeys(username);
    }

    /**
     * Enter password.
     *
     * @param password the password
     */
    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }

    /**
     * Click login.
     */
    public void clickLogin() {
        loginButton.click();
    }

    /**
     * Is login successful boolean.
     *
     * @return the boolean
     */
    public boolean isLoginSuccessful() {
        return shoppingCartBtn.isDisplayed();
    }

    /**
     * Open side menu.
     */
    public void openSideMenu() {
        waitForElementToBeVisible(By.xpath("//android.view.ViewGroup[@content-desc='open menu']/android.widget.ImageView"));
        sideMenu.click();
    }

    /**
     * Click login button.
     */
    public void clickLoginButton() {
        loginScreenInsideMenu.click();
    }

    /**
     * Click logout button.
     *
     * @throws InterruptedException the interrupted exception
     */
    public void clickLogoutButton() throws InterruptedException {
        waitForElementToBeVisible(By.xpath("//android.view.ViewGroup[@content-desc='menu item log out']"));
        logoutScreenInsideMenu.click();
    }

    /**
     * Click logout.
     */
    public void clickLogout() {
        logoutButton.click();
    }
}

