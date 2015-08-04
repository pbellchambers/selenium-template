package uk.co.pbellchambers.skeleton;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.pbellchambers.skeleton.pageobjects.Pages;

import static org.junit.Assert.assertTrue;

public class SearchSteps {

    private Pages pages;

    public SearchSteps(Pages pages) {
        this.pages = pages;
    }

    private static final Logger logger = LoggerFactory.getLogger(SearchSteps.class);

    @Given("^I am on the search homepage$")
    public void I_am_on_the_search_homepage() throws Throwable {
        logger.info("Loading homepage");
        pages.loadSite();
        pages.homePage().checkDisplayed();
        logger.info("Finished loading homepage");
    }

    @When("^I search for \"([^\"]*)\"$")
    public void I_search_for(String searchText) throws Throwable {
        logger.info("Searching for text: " + searchText);
        pages.homePage().doSearch(searchText);
        logger.info("Finished searching for text: " + searchText);
    }

    @Then("^I should see \"([^\"]*)\"$")
    public void I_should_see_Result(String expectedResult) throws Throwable {
        logger.info("Checking result displayed");
        pages.resultsPage().checkDisplayed();
        assertTrue(pages.resultsPage().getBodyText().contains(expectedResult));
        logger.info("Finished checking result displayed");
    }
}
