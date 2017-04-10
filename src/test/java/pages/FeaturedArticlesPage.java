package pages;

import com.lolatech.demo.Driver;
import com.lolatech.demo.LoggerHelper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.LoadableComponent;

import static org.junit.Assert.assertEquals;

/**
 * Created by razvan.vuscan on 3/21/17.
 */
public class FeaturedArticlesPage extends LoadableComponent<FeaturedArticlesPage> {
    private LoggerHelper loggerHelper;

    @FindBy(css = ".hlist>div>ul>li>a")
    private List<WebElement> featuredContentSection;

    private By featuredArticlesSectionsLocator = By.className("hlist");
    /**
     * Class constructor.
     */
    public FeaturedArticlesPage() {
        loggerHelper = new LoggerHelper(FeaturedArticlesPage.class);
    }

    @Override
    protected void load() {

    }

    @Override
    protected void isLoaded() throws Error {
        assertEquals("The Featured Articles sections are not present", 3, Driver.getDriver().findElements(featuredArticlesSectionsLocator).size());

        Driver.waitForPageToFinishLoading();
    }

    /**
     * Method used to select an article link from the appropriate section.
     *
     * @param article the article link, as a String.
     * @param section the section, as a String.
     */
    public void clickFeaturedArticleLink(String article, String section) {
        for (WebElement sec : featuredContentSection) {
            if (Driver.getText(sec, "the next section").equals(section)) {
                Driver.clickElement(sec, section);

                break;
            }
        }

        Driver.clickElement(Driver.getDriver().findElement(By.linkText(article)), article);
    }
}
