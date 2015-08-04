package uk.co.pbellchambers.skeleton.pageobjects;

import org.openqa.selenium.WebDriver;
import uk.co.pbellchambers.skeleton.support.WebDriverUtil;

import static org.junit.Assert.assertTrue;

public abstract class AbstractPage<T> extends WebDriverUtil {

    public AbstractPage(WebDriver driver) {
        super(driver);
    }

    @SuppressWarnings("unchecked")
    public T checkDisplayed() {
        assertTrue("Not on expected " + getClass().getSimpleName(), isDisplayed());
        return (T) this;
    }

    private boolean isDisplayed() {
        waitForPageLoaded();
        return true;
    }

    public String getTitle(){
        return driver.getTitle();
    }
}
