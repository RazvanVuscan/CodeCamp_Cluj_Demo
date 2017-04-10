package pages;

import com.lolatech.demo.Driver;
import com.lolatech.demo.LoggerHelper;
import com.lolatech.demo.TestData;

import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import static org.junit.Assert.assertEquals;

/**
 * Created by razvan.vuscan on 3/16/17.
 */
public class WikipediaHomePage extends LoadableComponent<WikipediaHomePage> {
    private LoggerHelper loggerHelper;

    @FindBy(id = "searchInput")
    private WebElement searchInputField;

    @FindBy(id = "searchButton")
    private WebElement searchButton;

    @FindBy(id="n-featuredcontent")
    private WebElement featuredContentLink;

    @FindBy(css = ".interlanguage-link-target")
    private List<WebElement> languageList;

    @FindBy(className = "MainPageLogo")
    private WebElement globalHeader;

    @FindBy(css = ".MainPageLogo>table>tbody>tr>td>span:nth-child(1)")
    private WebElement greetingMessage;

    private By globalHeaderLocator = By.className("MainPageLogo");

    /**
     * Class constructor.
     */
    public WikipediaHomePage() {
        loggerHelper = new LoggerHelper(WikipediaHomePage.class);
    }

    @Override
    protected void load() {
        Driver.getDriver().get(TestData.HOME_PAGE_URL.getTestDataBit());
    }

    @Override
    protected void isLoaded() throws Error {
        assertEquals("Search field not present", 1, Driver.getDriver().findElements(By.id("searchInput")).size());

        Driver.waitForPageToFinishLoading();
    }

    /**
     * Method used to populate the search field.
     *
     * @param term the term to populate the field with, as a String.
     */
    public void populateSearchField(String term) {
        searchInputField.sendKeys(term);
    }

    /**
     * Method used to click the search button.
     */
    public void clickSearchButton() {
        Driver.clickElement(searchButton, "Search button");
    }

    /**
     * Method used to click the featured content link.
     */
    public void clickFeaturedContentLink() {
        Driver.clickElement(featuredContentLink, "Featured content link");
    }

    /**
     * Method used to wait for the page to refresh.
     *
     * @throws TimeoutException .
     */
    public void waitForPageToRefresh() throws TimeoutException {
        Driver.waitForPageToFinishLoading();

        PageFactory.initElements(Driver.getDriver(), this);
    }

    /**
     * Method used to switch language.
     *
     * @param language the desired language, as a String.
     */
    public void switchLanguage(String language) {
        for (WebElement lang : languageList) {
            if (Driver.getText(lang, "the next language").equals(language)) {
                Driver.clickElement(lang, language);

                break;
            }
        }
    }

    /**
     * Method used to check the language change.
     *
     * @param language the language, as a String.
     * @throws TimeoutException .
     * @throws InterruptedException .
     */
    public void checkLanguageChange(String language) throws TimeoutException, InterruptedException {
        String message = null;

        switch(language) {
            case "Romanian":
                message = "Bun venit la Wikipedia!";

                break;

            case "English":
                message = "Welcome to Wikipedia";

                break;
        }

        Driver.waitForElementPresent(globalHeaderLocator);

        Driver.waitForElementDisplayed(globalHeader);

        assertEquals("The language is not the expected one", message, Driver.getText(greetingMessage, "Greeting message"));
    }
}
