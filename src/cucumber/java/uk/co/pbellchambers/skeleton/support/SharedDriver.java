package uk.co.pbellchambers.skeleton.support;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.impl.SimpleLogger;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class SharedDriver extends EventFiringWebDriver {
    private static WebDriver REAL_DRIVER;

    public static final String USERNAME = "USERNAME";
    public static final String AUTOMATE_KEY = "KEY";
    public static final String BROWSERSTACK_URL = "http://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";

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

        if(System.getProperty("isBrowserStackTest").equals("true") && System.getProperty("isMobileTest").equals("false")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("browser", System.getProperty("browser"));
            capabilities.setCapability("browser_version", System.getProperty("browserVersion"));
            capabilities.setCapability("os", System.getProperty("os"));
            capabilities.setCapability("os_version", System.getProperty("osVersion"));
            capabilities.setCapability("project", System.getProperty("project"));
            capabilities.setCapability("build", System.getProperty("build"));
            capabilities.setCapability("resolution", "1920x1080");
            capabilities.setCapability("browserstack.local", "true");
            capabilities.setCapability("browserstack.debug", "false");

            try {
                REAL_DRIVER = new RemoteWebDriver(new URL(BROWSERSTACK_URL), capabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else if(System.getProperty("isBrowserStackTest").equals("true") && System.getProperty("isMobileTest").equals("true")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("browserName", System.getProperty("mobileBrowserName"));
            capabilities.setCapability("platform", System.getProperty("mobilePlatform"));
            capabilities.setCapability("device", System.getProperty("mobileDevice"));
            capabilities.setCapability("project", System.getProperty("project"));
            capabilities.setCapability("build", System.getProperty("build"));
            capabilities.setCapability("browserstack.local", "true");
            capabilities.setCapability("browserstack.debug", "false");

            try {
                REAL_DRIVER = new RemoteWebDriver(new URL(BROWSERSTACK_URL), capabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else {
            FirefoxProfile firefoxProfile = new FirefoxProfile();
            REAL_DRIVER = new FirefoxDriver(firefoxProfile);

            //If you ever want to use chrome:
//            System.setProperty("webdriver.chrome.driver", "C:/SeleniumDrivers/chromedriver.exe");
//            REAL_DRIVER = new ChromeDriver();
        }
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
        if(System.getProperty("isMobileTest").equals("false")) {
            manage().window().maximize();
        }
        manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        manage().deleteAllCookies();
    }

    @After
    public void embedScreenshot(Scenario scenario) {
        if (scenario.isFailed()){
            try {
                byte[] screenshot = getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            } catch (WebDriverException somePlatformsDontSupportScreenshots) {
                System.err.println(somePlatformsDontSupportScreenshots.getMessage());
            }
        }
    }
}