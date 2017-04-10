package steps;

import org.openqa.selenium.support.PageFactory;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import com.lolatech.demo.Driver;
import pages.MystPage;

/**
 * Created by razvan.vuscan on 3/20/17.
 */
public class MystPageSteps {
    private MystPage mystPage;

    @Then("^I wait for the Myst page to load$")
    public void waitForPageToFinishLoading() {
        mystPage = PageFactory.initElements(Driver.getDriver(), MystPage.class).get();
    }

    @And("^I check if the Featured Star appears on the page$")
    public void iCheckIfTheFeaturedStarAppearsOnThePage() throws Throwable {
        mystPage.checkIfFeaturedStartIsPresent();
    }
}
