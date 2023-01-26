package uk.co.pbellchambers.seleniumtemplate.util;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.simple.SimpleLogger;

public class SharedDriver extends EventFiringWebDriver {
    private static WebDriver REAL_DRIVER;

    private static final Thread CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
            REAL_DRIVER.close();
            REAL_DRIVER.quit();
        }
    };

    static {
        System.setProperty(SimpleLogger.SHOW_DATE_TIME_KEY, "true");
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);

        WebDriverManager.chromedriver().setup();
        REAL_DRIVER = new ChromeDriver();
    }

    public SharedDriver() {
        super(REAL_DRIVER);
    }

    @Override
    public void quit() {
        if (Thread.currentThread() != CLOSE_THREAD) {
            throw new UnsupportedOperationException("You shouldn't close this WebDriver. It's shared and will close when the JVM exits.");
        }
        super.quit();
    }

    @Before
    public void deleteAllCookies() {
        REAL_DRIVER.manage().deleteAllCookies();
    }

    @After
    public void embedScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) REAL_DRIVER).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getId());
            } catch (WebDriverException somePlatformsDontSupportScreenshots) {
                System.err.println(somePlatformsDontSupportScreenshots.getMessage());
            }
        }
    }
}
