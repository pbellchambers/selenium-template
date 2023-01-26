package uk.co.pbellchambers.seleniumtemplate.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import uk.co.pbellchambers.seleniumtemplate.pageobjects.search.SearchHome;
import uk.co.pbellchambers.seleniumtemplate.pageobjects.search.SearchResults;
import uk.co.pbellchambers.seleniumtemplate.util.SharedDriver;

public class Pages {
    public WebDriver webDriver;
    public static final String MAIN_URL = "https://www.duckduckgo.com";
    private AbstractPage currentPage;

    public Pages(SharedDriver webDriver) {
        this.webDriver = webDriver;
    }

    @SuppressWarnings("unchecked")
    private <T extends AbstractPage<T>> T load(Class<T> pageClass) {
        if (pageClass.isInstance(currentPage)) {
            return (T) currentPage;
        } else {
            T page = PageFactory.initElements(webDriver, pageClass);
            currentPage = page;
            return page;
        }
    }

    public SearchHome loadSite() {
        webDriver.get(MAIN_URL);
        return load(SearchHome.class);
    }

    public SearchHome searchHome() {
        return load(SearchHome.class);
    }

    public SearchResults resultsPage() {
        return load(SearchResults.class);
    }
}