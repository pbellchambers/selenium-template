package uk.co.pbellchambers.seleniumtemplate.pageobjects.search;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import uk.co.pbellchambers.seleniumtemplate.pageobjects.AbstractPage;

public class SearchHome extends AbstractPage<SearchHome> {

    @FindBy(id = "searchbox_input")
    private WebElement searchField;

    @FindBy(id = "search_button_homepage")
    private WebElement searchButton;

    public SearchHome(WebDriver driver) {
        super(driver);
    }

    public void doSearch(String searchText) {
        waitForElementToBeVisible(searchField);

        searchField.clear();
        searchField.sendKeys(searchText);
        searchField.submit();
    }
}
