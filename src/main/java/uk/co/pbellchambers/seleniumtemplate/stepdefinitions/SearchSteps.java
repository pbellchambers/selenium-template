package uk.co.pbellchambers.seleniumtemplate.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.pbellchambers.seleniumtemplate.pageobjects.Pages;

import static org.junit.Assert.assertTrue;

public class SearchSteps {
    private Pages pages;

    public SearchSteps(Pages pages) {
        this.pages = pages;
    }

    private static final Logger logger = LoggerFactory.getLogger(SearchSteps.class);

    @Given("I am on the search homepage")
    public void iAmOnTheSearchHomepage() {
        logger.info("Loading homepage");
        pages.loadSite();
        pages.searchHome().checkDisplayed();
        logger.info("Finished loading homepage");
    }

    @When("I search for {string}")
    public void iSearchFor(String searchText) {
        logger.info("Searching for text: " + searchText);
        pages.searchHome().doSearch(searchText);
        logger.info("Finished searching for text: " + searchText);
    }

    @Then("I should see {string}")
    public void iShouldSee(String resultText) {
        logger.info("Checking result displayed");
        pages.resultsPage().checkDisplayed();
        assertTrue(pages.resultsPage().getBodyText().contains(resultText));
        logger.info("Finished checking result displayed");
    }
}
