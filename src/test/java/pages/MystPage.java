package pages;

import com.lolatech.demo.Driver;
import com.lolatech.demo.LoggerHelper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.LoadableComponent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by razvan.vuscan on 3/20/17.
 */
public class MystPage extends LoadableComponent<MystPage> {
    private LoggerHelper loggerHelper;

    @FindBy(id = "mw-indicator-featured-star")
    private WebElement featuredStar;

    private By quickInfoSectionLocator = By.cssSelector(".infobox.hproduct");

    /**
     * Class constructor.
     */
    public MystPage() {
        loggerHelper = new LoggerHelper(FeaturedContentPage.class);
    }

    @Override
    protected void load() {

    }

    @Override
    protected void isLoaded() throws Error {
        assertEquals("The Myst quick info section is not displayed", 1, Driver.getDriver().findElements(quickInfoSectionLocator).size());
    }

    public void checkIfFeaturedStartIsPresent() {
        assertTrue("Featured star not displayed", Driver.isElementDisplayed(featuredStar));
    }
}
