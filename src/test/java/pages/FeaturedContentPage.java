package pages;

import com.lolatech.demo.Driver;
import com.lolatech.demo.LoggerHelper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.LoadableComponent;

import static org.junit.Assert.assertEquals;

/**
 * Created by razvan.vuscan on 3/20/17.
 */
public class FeaturedContentPage extends LoadableComponent<FeaturedContentPage> {
    private LoggerHelper loggerHelper;

    @FindBy(className = "MainPageBG")
    private WebElement featuredContentParagraph;

    @FindBy(css = "#mw-content-text>table>tbody>tr>td>ul>li>a[title*='Wikipedia:Featured articles']")
    private WebElement featuredArticlesLink;

    private By featuredContentParagraphLocator = By.className("MainPageBG");
    private By featuredContentLinksLocator = By.cssSelector("#mw-content-text>table>tbody>tr>td:nth-child(2)");

    /**
     * Class constructor.
     */
    public FeaturedContentPage() {
        loggerHelper = new LoggerHelper(FeaturedContentPage.class);
    }

    @Override
    protected void load() {

    }

    @Override
    protected void isLoaded() throws Error {
        assertEquals("The Featured Content paragraph is not present", 1, Driver.getDriver().findElements(featuredContentParagraphLocator).size());
        assertEquals("The Featured Content links is not present", 1, Driver.getDriver().findElements(featuredContentLinksLocator).size());

        Driver.waitForPageToFinishLoading();
    }

    /**
     * Method used to click the featured articles link.
     */
    public void clickFeaturedArticlesLink(){
        Driver.clickElement(featuredArticlesLink,"Featured articles");
    }


}
