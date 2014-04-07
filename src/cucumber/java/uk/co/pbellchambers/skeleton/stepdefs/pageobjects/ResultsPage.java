package uk.co.pbellchambers.skeleton.stepdefs.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResultsPage {

    protected WebDriver driver;

    @FindBy(tagName="body")
    private WebElement bodyElement;

    @FindBy(id="r1-0")
    private WebElement resultElement;


    public ResultsPage(WebDriver driver){
        this.driver = driver;
    }

    public String getBodyText(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(resultElement));

        return bodyElement.getText();
    }
}