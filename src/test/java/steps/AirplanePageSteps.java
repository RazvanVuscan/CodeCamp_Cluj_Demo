package steps;

import com.lolatech.demo.Driver;

import org.openqa.selenium.support.PageFactory;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import pages.AirplanePage;

/**
 * Created by razvan.vuscan on 3/17/17.
 */

public class AirplanePageSteps {
    private AirplanePage airplanePage;

    @Then("^I wait for the Airplane page to load$")
    public void waitForPageToLoad() {
        airplanePage = PageFactory.initElements(Driver.getDriver(), AirplanePage.class).get();
    }

    @And("^I check that the title matches$")
    public void checkThatTitleMatches() throws Throwable {
        airplanePage.checkTitleMatch();
    }
}



