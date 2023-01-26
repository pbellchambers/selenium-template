package uk.co.pbellchambers.seleniumtemplate.pageobjects.search;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import uk.co.pbellchambers.seleniumtemplate.pageobjects.AbstractPage;

public class SearchResults extends AbstractPage<SearchResults> {

    @FindBy(tagName = "body")
    private WebElement bodyElement;

    @FindBy(id = "r1-0")
    private WebElement resultElement;

    public SearchResults(WebDriver driver) {
        super(driver);
    }

    public String getBodyText() {
        waitForElementToBeVisible(resultElement);

        return bodyElement.getText();
    }
}