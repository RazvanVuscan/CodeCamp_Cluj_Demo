package com.lolatech.demo;

import com.google.common.base.Function;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;

/**
 * Created by razvan.vuscan on 3/16/17.
 */
public class Driver {
    private static LoggerHelper loggerHelper;

    static Map<Long, WebDriver> driverMap = new HashMap<>();

    private static final String PROTOCOL = TestData.NON_SECURE_PROTOCOL.getTestDataBit();
    private static final String DOCKER_IP = TestData.DOCKER_IP.getTestDataBit();
    private static final String DOCKER_PORT = TestData.DOCKER_PORT.getTestDataBit();
    private static final String WEB_DRIVER_HUB = TestData.WEB_DRIVER_HUB.getTestDataBit();

    private static final String dockerUrl = PROTOCOL + DOCKER_IP + ":" + DOCKER_PORT + WEB_DRIVER_HUB;

    /**
     * Method used to return the WebDriver instance.
     *
     * @return the WebDriver instance.
     */
    public static WebDriver getDriver() {
        return driverMap.get(Thread.currentThread().getId());
    }

    /**
     * Method used to initialize a new WebDriver instance.
     *
     * @param browser the browser type.
     * @throws MalformedURLException .
     */
    public static void initDriver(BrowserType browser) throws MalformedURLException {
        loggerHelper = new LoggerHelper(Driver.class);

        switch (browser) {
            case FIREFOX:
                driverMap.put(Thread.currentThread().getId(), initFirefoxDriver());

                break;

            case CHROME:
                driverMap.put(Thread.currentThread().getId(), initChromeDriver());

                break;
        }
    }

    /**
     * Method used to initialize the Firefox Driver.
     *
     * @return the Firefox Driver instance.
     * @throws MalformedURLException .
     */
    private static WebDriver initFirefoxDriver() throws MalformedURLException {
        String geckoDriverLocation = System.getProperty("user.dir") + "/browser_drivers/geckodriver";

        System.setProperty("webdriver.gecko.driver", geckoDriverLocation);

        DesiredCapabilities firefoxCapabilities = DesiredCapabilities.firefox();
        firefoxCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        firefoxCapabilities.setCapability(CapabilityType.HAS_NATIVE_EVENTS, true);
        firefoxCapabilities.setCapability(CapabilityType.SUPPORTS_ALERTS, true);

        return new RemoteWebDriver(new URL(dockerUrl), firefoxCapabilities);
    }

    /**
     * Method used to initialize the Chrome Driver.
     *
     * @return the Chrome Driver instance.
     * @throws MalformedURLException .
     */
    private static WebDriver initChromeDriver() throws MalformedURLException {
        String chromeDriverLocation = System.getProperty("user.dir") + "/browser_drivers/chromedriver";

        System.setProperty("webdriver.chrome.driver", chromeDriverLocation);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");

        DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();
        chromeCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        chromeCapabilities.setCapability(CapabilityType.HAS_NATIVE_EVENTS, true);
        chromeCapabilities.setCapability(CapabilityType.SUPPORTS_ALERTS, true);
        chromeCapabilities.setCapability(ChromeOptions.CAPABILITY, options);

        return new RemoteWebDriver(new URL(dockerUrl), chromeCapabilities);
    }

    /**
     * Method used to quit the WebDriver isntance.
     */
    public static void quit() {
        Driver.getDriver().quit();
    }

    /**
     * Method used to click an element.
     *
     * @param element     the WebElement.
     * @param elementName the element name.
     */
    public static void clickElement(WebElement element, String elementName) {
        loggerHelper.logInfo(String.format("Clicking on the %s element", elementName));

        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();

        try {

            element.click();

        } catch (Exception e) {

            loggerHelper.logInfo("Retrying action...");

            jse.executeScript("arguments[0].click();", element);

        }
    }

    /**
     * Method used to get the text of an element.
     *
     * @param element     the WebElement.
     * @param elementName the element name.
     * @return the text value.
     */
    public static String getText(WebElement element, String elementName) {
        String textValue;

        loggerHelper.logInfo(String.format("Getting the text value from the %s ", elementName));

        try {

            textValue = element.getText();

        } catch (StaleElementReferenceException sere) {
            loggerHelper.logInfo("StaleElementReferenceException exception caught... Retrying action...");

            Driver.getDriver().switchTo().defaultContent();

            textValue = (String) ((JavascriptExecutor) Driver.getDriver()).executeScript("return arguments[0].text", element);
        }

        return textValue;
    }

    /**
     * Method used to wait until an element is displayed on the page.
     *
     * @param element the WebElement.
     * @throws TimeoutException     .
     * @throws InterruptedException .
     */
    public static void waitForElementDisplayed(WebElement element) throws TimeoutException, InterruptedException {
        new FluentWait<>(element)
            .withTimeout(10, TimeUnit.SECONDS)
            .pollingEvery(500, TimeUnit.MILLISECONDS)
            .ignoreAll(Arrays.asList(NoSuchElementException.class, StaleElementReferenceException.class))
            .withMessage("TimeoutException was thrown because the element was not present")
            .until(new Function<WebElement, Boolean>() {
                @Override
                public Boolean apply(WebElement element) {
                    return element.isDisplayed();
                }
        });
    }

    /**
     * Method used to check if an element is present.
     *
     * @param locator the locator of the WebElement.
     */
    public static void waitForElementPresent(By locator) {
        new FluentWait<>(Driver.getDriver())
            .withTimeout(10, TimeUnit.SECONDS)
            .pollingEvery(500, TimeUnit.MILLISECONDS)
            .ignoreAll(Arrays.asList(NoSuchElementException.class, StaleElementReferenceException.class, org.openqa.selenium.TimeoutException.class))
            .withMessage("TimeoutException was thrown because the element was not present")
            .until(new Function<WebDriver, Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return Driver.getDriver().findElements(locator).size() > 0;
                    }
            });
    }

    /**
     * Method used to check if an element is displayed.
     *
     * @param element the WebElement.
     */
    public static Boolean isElementDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    /**
     * Method used to wait for the AJAX calls to finish, before continuing.
     */
    public static void waitForPageToFinishLoading() {
        loggerHelper.getStartTime("Waiting for any jQuery and AJAX calls to finish");

        new FluentWait<>(Driver.getDriver())
                .withTimeout(15, TimeUnit.SECONDS)
                .pollingEvery(100, TimeUnit.MILLISECONDS)
                .ignoreAll(Arrays.asList(NoSuchElementException.class, StaleElementReferenceException.class))
                .withMessage("TimeoutException was thrown because the element was not present")
                .until(new Function<WebDriver, Boolean>() {
                    public Boolean apply(WebDriver input) {
                        return (Boolean) ((JavascriptExecutor) Driver.getDriver()).executeScript("return (window.jQuery != null) && (jQuery.active === 0);")
                            && (Boolean) ((JavascriptExecutor) Driver.getDriver()).executeScript("return document.readyState").equals("complete");
                }
        });

        loggerHelper.getEndTime("wait for page calls to finish on", "Page");
    }

}
