package steps;

import com.lolatech.demo.BrowserType;
import com.lolatech.demo.Driver;

import java.net.MalformedURLException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

/**
 * Created by razvan.vuscan on 3/16/17.
 */

public class TestSetup {

    @Before("@Firefox")
    public static void setupFirefox() throws MalformedURLException {
        Driver.initDriver(BrowserType.FIREFOX);
    }

    @Before("@Chrome")
    public static void setupChrome() throws MalformedURLException {
        Driver.initDriver(BrowserType.CHROME);
    }

    @After()
    public static void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {

            byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);

            scenario.embed(screenshot, "image/png");

            Driver.quit();

        } else {

            Driver.quit();

        }

        System.out.println("Scenario: " + scenario.getName() + " ------ " + scenario.getStatus() + "\n");
    }
}


