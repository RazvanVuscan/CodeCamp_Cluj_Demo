package steps;

import org.openqa.selenium.support.PageFactory;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import com.lolatech.demo.Driver;
import pages.FeaturedContentPage;

/**
 * Created by razvan.vuscan on 3/20/17.
 */
public class FeaturedContentPageSteps {
    private FeaturedContentPage featuredContentPage;

    @Then("^I wait for the Featured page to load$")
    public void waitForPageToFinishLoading() {
        featuredContentPage = PageFactory.initElements(Driver.getDriver(), FeaturedContentPage.class).get();
    }

    @When("^I click on the Featured articles link from the Featured content page$")
    public void iClickOnTheFeaturedArticlesLinkFromTheFeaturedContentPage() throws Throwable {
        featuredContentPage.clickFeaturedArticlesLink();
    }
}
