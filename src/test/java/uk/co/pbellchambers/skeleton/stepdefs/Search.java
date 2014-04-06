package uk.co.pbellchambers.skeleton.stepdefs;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import uk.co.pbellchambers.skeleton.stepdefs.pageobjects.Homepage;
import uk.co.pbellchambers.skeleton.stepdefs.pageobjects.ResultsPage;
import uk.co.pbellchambers.skeleton.stepdefs.support.SharedDriver;

import static org.junit.Assert.assertTrue;

public class Search {

    private final WebDriver webDriver;
    private Homepage homepage;
    private ResultsPage resultsPage;

    public Search(SharedDriver webDriver){
        this.webDriver = webDriver;
    }

    @Given("^I am on the Google homepage$")
    public void I_am_on_the_Google_homepage() throws Throwable {
        homepage = PageFactory.initElements(webDriver, Homepage.class);
        homepage.open();
    }

    @When("^I search for all of the below:$")
    public void I_search_for_all_of_the_below() throws Throwable {
        // Express the Regexp above with the code you wish you had
        throw new PendingException();
    }

    @When("^I search for (.*)$")
    public void I_search_for(String searchText) throws Throwable {
        homepage.doGoogleSearch(searchText);
    }

    @Then("^I should see (.*)$")
    public void I_should_see_Result(String expectedResult) throws Throwable {
        resultsPage = PageFactory.initElements(webDriver, ResultsPage.class);
        assertTrue(resultsPage.getBodyText().contains(expectedResult));
    }
}
