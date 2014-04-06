package uk.co.pbellchambers.skeleton.stepdefs.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Homepage {

    protected WebDriver driver;
    private String pageUrl = "http://www.duckduckgo.com";

    @FindBy(id="search_form_input_homepage")
    private WebElement searchField;

    @FindBy(id="search_button_homepage")
    private WebElement searchButton;


    public Homepage(WebDriver driver){
        this.driver = driver;
    }

    public void open(){
        driver.get(pageUrl);
    }

    public void doGoogleSearch(String searchText){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(searchField));

        searchField.clear();
        searchField.sendKeys(searchText);
        searchField.submit();
    }
}
