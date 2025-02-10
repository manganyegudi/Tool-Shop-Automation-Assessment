package com.toolshop.base;

import com.toolshop.drivers.DriverFactory;
import com.toolshop.utils.LoggerUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class BasePage {

    private static final Logger logger = LoggerUtils.getLogger(BasePage.class);
    private final DriverFactory driverFactory = DriverFactory.getInstance();


    public void sendKeys(WebElement element, String keys, String elementName) {
        try {
            waitForElementVisibility(element);
            element.sendKeys(keys);
            logger.info("Element {} has been entered", elementName);
        } catch (Exception error) {
            throw new RuntimeException(error.getMessage(), error);
        }
    }

    public void clickElement(WebElement element, String elementName) {
        try {
            waitForElementVisibility(element);
            waitForElementToBeClickable(element);
            element.click();
            logger.info("Element {} has been clicked", elementName);
        } catch (Exception error) {
            throw new RuntimeException(error.getMessage(), error);
        }
    }

    public void clearElement(WebElement element, String elementName) {
        try {
            waitForElementVisibility(element);
            element.clear();
            logger.info("Element {} has been cleared", elementName);
        } catch (Exception error) {
            throw new RuntimeException(error.getMessage(), error);
        }
    }

    public void selectFromDropdown(WebElement dropdownElement, String selectionMethod, String value, String elementName) {
        try {
            Select select = new Select(dropdownElement);

            switch (selectionMethod.toLowerCase()) {
                case "visibletext":
                    select.selectByVisibleText(value);
                    logger.info("Element {} has been selected using 'Visible Text' ", elementName);
                    break;

                case "value":
                    select.selectByValue(value);
                    logger.info("Element {} has been selected using 'Value' ", elementName);
                    break;

                case "index":
                    try {
                        select.selectByIndex(Integer.parseInt(value));
                        logger.info("Element {} has been selected using 'Index' ", elementName);
                        break;

                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Invalid index value: '" + value + "'. It must be a valid integer.", e);
                    }

                default:
                    throw new IllegalArgumentException("Invalid selection method: '" + selectionMethod + "'. Use 'visibletext', 'value', or 'index'.");
            }
        } catch (Exception error) {
            throw new RuntimeException("Failed to select from dropdown: " + error.getMessage(), error);
        }
    }

    public void isElementVisible(WebElement element, String elementName) {
        try {
            if (element.isDisplayed()) {
                logger.info("Element '{}' is visible", elementName);
            } else {
                logger.error("Element '{}' is not visible", elementName);
                throw new NoSuchElementException("Element '" + elementName + "' is not visible.");
            }
        } catch (Exception error) {
            throw new RuntimeException("Failed to check visibility of element: " + elementName, error);
        }
    }

    public void isElementNotVisible(WebElement element, String elementName) {
        try {
            if (!element.isDisplayed()) {
                logger.info("Element '{}' is not visible, as expected", elementName);
            } else {
                logger.error("Element '{}' is unexpectedly visible", elementName);
                throw new AssertionError("Element '" + elementName + "' is unexpectedly visible.");
            }
        } catch (NoSuchElementException e) {
            // Expected case: Element is not present in DOM, which means it's not visible
            logger.info("Element '{}' is not present in the DOM, which confirms invisibility", elementName);
        } catch (Exception error) {
            throw new RuntimeException("Failed to check invisibility of element: " + elementName, error);
        }
    }

    public void waitForElementVisibility(WebElement element) {
        Wait<WebDriver> wait = initFluentWait();
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToBeClickable(WebElement element) {
        Wait<WebDriver> wait = initFluentWait();
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementToBeSelected(WebElement element) {
        Wait<WebDriver> wait = initFluentWait();
        wait.until(ExpectedConditions.elementToBeSelected(element));
    }

    private Wait<WebDriver> initFluentWait() {
        try {
            return new FluentWait<>(driverFactory.getDriver())
                    .withTimeout(Duration.ofSeconds(45))
                    .pollingEvery(Duration.ofMillis(1000))
                    .ignoring(WebDriverException.class);
        } catch (Exception error) {
            throw new RuntimeException(error.getMessage(), error);
        }
    }
}
