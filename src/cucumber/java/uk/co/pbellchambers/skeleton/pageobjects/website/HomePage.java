package uk.co.pbellchambers.skeleton.pageobjects.website;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import uk.co.pbellchambers.skeleton.pageobjects.AbstractPage;

public class HomePage extends AbstractPage<HomePage> {

    @FindBy(id="search_form_input_homepage")
    private WebElement searchField;

    @FindBy(id="search_button_homepage")
    private WebElement searchButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void doSearch(String searchText){
        waitForElementToBeVisible(searchField);

        searchField.clear();
        searchField.sendKeys(searchText);
        searchField.submit();
    }
}
