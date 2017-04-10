package pages;

import com.lolatech.demo.Driver;
import com.lolatech.demo.LoggerHelper;

import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.LoadableComponent;

import static org.junit.Assert.assertEquals;

/**
 * Created by razvan.vuscan on 3/17/17.
 */
public class AirplanePage extends LoadableComponent<AirplanePage> {
    private LoggerHelper loggerHelper;

    private final String EXPECTED_TITLE = "Airplane";

    @FindBy(id = "firstHeading")
    private WebElement heading;

    private By headingLocator = By.id("firstHeading");

    /**
     * Class constructor.
     */
    public AirplanePage() {
        loggerHelper = new LoggerHelper(AirplanePage.class);
    }

    @Override
    protected void load() {

    }

    @Override
    protected void isLoaded() throws Error {
        assertEquals("Heading is not present",1, Driver.getDriver().findElements(headingLocator).size());

        Driver.waitForPageToFinishLoading();
    }

    /**
     * Method used to check that the title matches.
     */
    public void checkTitleMatch() throws TimeoutException, InterruptedException {
        Driver.waitForElementPresent(headingLocator);

        Driver.waitForElementDisplayed(heading);

        assertEquals("Heading title does not match", EXPECTED_TITLE, Driver.getText(heading, "the heading"));
    }
}
