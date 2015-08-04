package uk.co.pbellchambers.skeleton.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import uk.co.pbellchambers.skeleton.pageobjects.website.HomePage;
import uk.co.pbellchambers.skeleton.pageobjects.website.ResultsPage;
import uk.co.pbellchambers.skeleton.support.SharedDriver;

public class Pages {
    public WebDriver webDriver;
    public static final String MAIN_URL = "http://www.duckduckgo.com";
    private AbstractPage currentPage;

    public Pages(SharedDriver webDriver) {
        this.webDriver = webDriver;
    }

    @SuppressWarnings("unchecked")
    private <T extends AbstractPage<T>> T load(Class<T> pageClass) {
        if(pageClass.isInstance(currentPage)){
            return (T)currentPage;
        } else {
            T page = PageFactory.initElements(webDriver, pageClass);
            currentPage = page;
            return page;
        }
    }

    public HomePage loadSite() {
        webDriver.get(MAIN_URL);
        return load(HomePage.class);
    }

    public HomePage homePage() {
        return load(HomePage.class);
    }

    public ResultsPage resultsPage() {
        return load(ResultsPage.class);
    }
}