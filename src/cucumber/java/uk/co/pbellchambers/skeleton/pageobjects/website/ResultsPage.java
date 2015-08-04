package uk.co.pbellchambers.skeleton.pageobjects.website;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import uk.co.pbellchambers.skeleton.pageobjects.AbstractPage;

public class ResultsPage extends AbstractPage<ResultsPage> {

    @FindBy(tagName="body")
    private WebElement bodyElement;

    @FindBy(id="r1-0")
    private WebElement resultElement;

    public ResultsPage(WebDriver driver) {
        super(driver);
    }

    public String getBodyText(){
        waitForElementToBeVisible(resultElement);

        return bodyElement.getText();
    }
}