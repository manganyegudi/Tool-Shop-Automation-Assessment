package com.toolshop.drivers;

import org.openqa.selenium.WebDriver;

public class DriverFactory {

    private static final DriverFactory instance = new DriverFactory();

    private final ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<WebDriver>();

    private DriverFactory() {}

    public static DriverFactory getInstance() {
        return instance;
    }

    public WebDriver getDriver() {
        return threadLocalDriver.get();
    }

    public void setDriver(WebDriver driver) {
        threadLocalDriver.set(driver);
    }

    public void quitDriver() {
        try {
            WebDriver driver = threadLocalDriver.get();

            if (driver != null) {
                Thread.sleep(5000);
                driver.quit();
            }
        } catch (Exception error) {
            throw new RuntimeException("Failed to quite driver", error);
        } finally {
            threadLocalDriver.remove();
        }
    }

    public void navigateToUrl(String url) {
        getDriver().get(url);
    }
}
