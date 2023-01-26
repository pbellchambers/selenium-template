package uk.co.pbellchambers.seleniumtemplate.util;

import com.google.common.base.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class WebDriverUtil {
    protected final WebDriver driver;

    public WebDriverUtil(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriverWait wait;
    public Duration waitTimeoutSeconds = Duration.ofSeconds(30);
    public Duration secondsForNotVisible = Duration.ofSeconds(5);
    public Duration pollingIntervalMs = Duration.ofMillis(10);

    private static final Logger logger = LoggerFactory.getLogger(WebDriverUtil.class);

    /**
     * Wait for the page to load
     */
    public void waitForPageLoaded() {
        ExpectedCondition<Boolean> expectation = driver1 -> ((JavascriptExecutor) driver1).executeScript("return document.readyState").equals("complete");
        wait = new WebDriverWait(driver, waitTimeoutSeconds, pollingIntervalMs);
        try {
            wait.until(expectation);
        } catch (Throwable error) {
            logger.warn("Error waiting for Page Load Request to complete. Continuing anyway...");
        }
    }

    /**
     * wait for element to be visible
     *
     * @param element pass the WebElement
     */
    public void waitForElementToBeVisible(WebElement element) {
        logger.info("Start verifying that element is visible: " + getElementLocatorString(element));
        try {
            wait = new WebDriverWait(driver, waitTimeoutSeconds, pollingIntervalMs);
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (StaleElementReferenceException e) {
            logger.warn("Attempting to recover from StaleElementReferenceException");
            waitForElementToBeVisible(element);
        }
        moveToElement(element);
        logger.info("finish verifying element is visible: " + getElementLocatorString(element));
    }

    private String getElementLocatorString(WebElement element) {
        return StringUtils.substringAfter(element.toString(), "> ");
    }

    private void moveToElement(WebElement element) {
        logger.info("Moving to element: " + getElementLocatorString(element));
        actionMove(element);
        logger.info("Finished moving to element: " + getElementLocatorString(element));
    }

    private void actionMove(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    /**
     * Wait for Element to exist
     *
     * @param locator pass the locator any locator ex, By.xpath, By.cssSelector etc
     */
    public void waitForElementToExist(By locator) {
        logger.info("verifying element exists: " + locator);
        try {
            wait = new WebDriverWait(driver, waitTimeoutSeconds, pollingIntervalMs);
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            WebElement element = driver.findElement(locator);
        } catch (StaleElementReferenceException e) {
            logger.info("Attempting to recover from StaleElementReferenceException");
            waitForElementToExist(locator);
        }
        logger.info("finish verifying element exists: " + locator);
    }

    /**
     * Wait for Element to be clickable
     *
     * @param element pass the WebElement
     */
    public void waitForElementToBeClickable(WebElement element) {
        logger.info("verifying that element is clickable: " + getElementLocatorString(element));
        try {
            Thread.sleep(250); //Due to some angular issue
            wait = new WebDriverWait(driver, waitTimeoutSeconds, pollingIntervalMs);
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (StaleElementReferenceException e) {
            logger.warn("Attempting to recover from StaleElementReferenceException");
            waitForElementToBeClickable(element);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        moveToElement(element);
        logger.info("finish verifying element is clickable: " + getElementLocatorString(element));
    }

    /**
     * Wait for Element to contain text
     *
     * @param element      pass the WebElement
     * @param expectedText pass the text to look for
     */
    public void waitForElementToContainText(WebElement element, String expectedText) {
        logger.info("verifying element contains text: " + getElementLocatorString(element) + " " + expectedText);
        try {
            wait = new WebDriverWait(driver, waitTimeoutSeconds, pollingIntervalMs);
            wait.until(ExpectedConditions.textToBePresentInElement(element, expectedText));
        } catch (StaleElementReferenceException e) {
            logger.warn("Attempting to recover from StaleElementReferenceException");
            waitForElementToContainText(element, expectedText);
        }
        logger.info("finish verifying element contains text: " + getElementLocatorString(element) + " " + expectedText);
    }

    /**
     * Wait for Element to contain attribute
     *
     * @param element       pass the WebElement
     * @param attribute     pass the attribute of the element to check
     * @param expectedValue pass the expected value of the attribute
     */
    public void waitForElementToContainAttribute(WebElement element, String attribute, String expectedValue) {
        logger.info("verifying element contains attribute");
        try {
            wait = new WebDriverWait(driver, waitTimeoutSeconds, pollingIntervalMs);

            wait.until(new ExpectedCondition<Boolean>() {
                private WebElement element;
                private String attribute;
                private String expectedValue;

                private ExpectedCondition<Boolean> init(WebElement element, String attribute, String expectedValue) {
                    this.element = element;
                    this.attribute = attribute;
                    this.expectedValue = expectedValue;
                    return this;
                }

                public Boolean apply(WebDriver driver) {
                    String attr = element.getAttribute(this.attribute);
                    return attr.contains(this.expectedValue);
                }
            }.init(element, attribute, expectedValue));
        } catch (StaleElementReferenceException e) {
            logger.warn("Attempting to recover from StaleElementReferenceException");
            waitForElementToContainAttribute(element, attribute, expectedValue);
        }
        logger.info("finish verifying contains attribute");
    }

    /**
     * Wait for Element to not contain attribute
     *
     * @param element       pass the WebElement
     * @param attribute     pass the attribute of the element to check
     * @param expectedValue pass the expected value of the attribute
     */
    public void waitForElementToNotContainAttribute(WebElement element, String attribute, String expectedValue) {
        logger.info("verifying element not contains attribute");
        try {
            wait = new WebDriverWait(driver, waitTimeoutSeconds, pollingIntervalMs);

            wait.until(new ExpectedCondition<Boolean>() {
                private WebElement element;
                private String attribute;
                private String expectedValue;

                private ExpectedCondition<Boolean> init(WebElement element, String attribute, String expectedValue) {
                    this.element = element;
                    this.attribute = attribute;
                    this.expectedValue = expectedValue;
                    return this;
                }

                public Boolean apply(WebDriver driver) {
                    String attr = element.getAttribute(this.attribute);
                    return !attr.contains(this.expectedValue);
                }
            }.init(element, attribute, expectedValue));
        } catch (StaleElementReferenceException e) {
            logger.warn("Attempting to recover from StaleElementReferenceException");
            waitForElementToContainAttribute(element, attribute, expectedValue);
        }
        logger.info("finish verifying not contains attribute");
    }

    /**
     * Wait for Element to contain css value
     *
     * @param element       pass the WebElement
     * @param attribute     pass the css value to check
     * @param expectedValue pass the expected value of the attribute
     */
    public void waitForElementToContainCssValue(WebElement element, String attribute, String expectedValue) {
        logger.info("verifying element contains CSS value");
        try {
            wait = new WebDriverWait(driver, waitTimeoutSeconds, pollingIntervalMs);

            wait.until(new ExpectedCondition<Boolean>() {
                private WebElement element;

                private String attribute;

                private String expectedValue;

                private ExpectedCondition<Boolean> init(WebElement element, String attribute, String expectedValue) {
                    this.element = element;
                    this.attribute = attribute;
                    this.expectedValue = expectedValue;
                    return this;
                }

                public Boolean apply(WebDriver driver) {
                    String attr = element.getCssValue(this.attribute);
                    return attr.contains(this.expectedValue);
                }
            }.init(element, attribute, expectedValue));
        } catch (StaleElementReferenceException e) {
            logger.warn("Attempting to recover from StaleElementReferenceException");
            waitForElementToContainCssValue(element, attribute, expectedValue);
        }
        logger.info("finish verifying contains attribute");
    }

    /**
     * Add wait time in seconds
     *
     * @param waitTime enter a numeric value in seconds
     */
    public void waitForTime(int waitTime) {
        logger.info("Starting to wait for: " + waitTime * 1000);
        try {
            Thread.sleep(waitTime * 1000);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
        logger.info("Finished waiting for: " + waitTime * 1000);
    }

    protected void waitForTextInElement(final String elementSelector) {
        ExpectedCondition<Boolean> condition = driver1 -> {
            String elementText = (String) ((JavascriptExecutor) driver1).executeScript("return $('" + elementSelector + "').html()");
            return elementText != null && !elementText.isEmpty();
        };
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5), pollingIntervalMs);
        wait.until(condition);
    }

    protected void waitForTextInElement(final String elementSelector, final String expectedText) {
        ExpectedCondition<Boolean> condition = driver1 -> {
            String elementText = (String) ((JavascriptExecutor) driver1).executeScript("return $('" + elementSelector + "').html()");
            return elementText.equals(expectedText);
        };
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5), pollingIntervalMs);
        wait.until(condition);
    }

    protected void waitForTextInElementToContain(final String elementSelector, final String expectedText) {
        ExpectedCondition<Boolean> condition = driver1 -> {
            String elementText = (String) ((JavascriptExecutor) driver1).executeScript("return $('" + elementSelector + "').html()");
            return elementText != null && elementText.contains(expectedText);
        };
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5), pollingIntervalMs);
        wait.until(condition);
    }

    public void waitForElementNotToBeVisible(By locator) {
        logger.info("verifying that element is not visible: " + locator);
        wait = new WebDriverWait(driver, secondsForNotVisible, pollingIntervalMs);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        logger.info("finish verifying element is not visible: " + locator);
    }

    private class ElementHasAttribute implements Predicate<WebDriver> {
        private WebElement element;

        private String attributeName;

        private ElementHasAttribute(WebElement element, String attributeName) {
            this.element = element;
            this.attributeName = attributeName;
        }

        @Override
        public boolean apply(WebDriver input) {
            String attribute = element.getAttribute(attributeName);
            return attribute != null && attribute.length() > 0;
        }

    }
}
