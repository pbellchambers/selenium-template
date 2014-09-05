package uk.co.pbellchambers.skeleton.stepdefs;

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

    @Given("^I am on the search homepage$")
    public void I_am_on_the_search_homepage() throws Throwable {
        homepage = PageFactory.initElements(webDriver, Homepage.class);
        homepage.open();
    }

    @When("^I search for \"([^\"]*)\"$")
    public void I_search_for(String searchText) throws Throwable {
        homepage.doSearch(searchText);
    }

    @Then("^I should see \"([^\"]*)\"$")
    public void I_should_see_Result(String expectedResult) throws Throwable {
        resultsPage = PageFactory.initElements(webDriver, ResultsPage.class);
        assertTrue(resultsPage.getBodyText().contains(expectedResult));
    }
}
