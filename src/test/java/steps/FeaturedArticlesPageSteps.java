package steps;

import org.openqa.selenium.support.PageFactory;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import com.lolatech.demo.Driver;
import pages.FeaturedArticlesPage;

/**
 * Created by razvan.vuscan on 3/21/17.
 */
public class FeaturedArticlesPageSteps {
    private FeaturedArticlesPage featuredArticlesPage;

    @Then("^I wait for the Featured articles page to load$")
    public void waitForPageToLoad() {
        featuredArticlesPage = PageFactory.initElements(Driver.getDriver(), FeaturedArticlesPage.class).get();
    }

    @When("^I click on the (.*) link from the (.*) section of the Featured articles page$")
    public void iClickOnTheMystLinkFromTheVideoGamingSectionOfTheFeaturedArticlesPage(String article, String section) throws Throwable {
        featuredArticlesPage.clickFeaturedArticleLink(article, section);
    }
}
