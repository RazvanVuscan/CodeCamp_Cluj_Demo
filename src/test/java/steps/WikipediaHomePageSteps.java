package steps;

import com.lolatech.demo.Driver;

import org.openqa.selenium.support.PageFactory;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.WikipediaHomePage;

/**
 * Created by razvan.vuscan on 3/17/17.
 */
public class WikipediaHomePageSteps {
    private WikipediaHomePage wikipediaHomePage;

    @Given("^I navigate to the Wikipedia homepage$")
    public void navigateToWikipediaHomePage() {
        wikipediaHomePage = PageFactory.initElements(Driver.getDriver(), WikipediaHomePage.class).get();
    }

    @When("^I enter (.*) in the search field$")
    public void searchForTerm(String term) throws Throwable {
        wikipediaHomePage.populateSearchField(term);
    }

    @And("^I click the search button$")
    public void clickSearchButton() throws Throwable {
        wikipediaHomePage.clickSearchButton();
    }

    @When("^I click the Featured content link from the left-hand side menu of the Wikipedia homepage$")
    public void clickFeaturedContentLink() throws Throwable {
        wikipediaHomePage.clickFeaturedContentLink();
    }

    @When("^I click on the (.*) language link on the right-hand side menu$")
    public void clickOnLanguageLink(String language) throws Throwable {
        wikipediaHomePage.switchLanguage(language);
    }

    @And("^I wait for the page to refresh$")
    public void waitForPageToRefresh() throws Throwable {
        wikipediaHomePage.waitForPageToRefresh();
    }

    @Then("^I check that the language on the homepage has switched to (.*)$")
    public void checkLanguageChange(String language) throws Throwable {
        wikipediaHomePage.checkLanguageChange(language);
    }
}
