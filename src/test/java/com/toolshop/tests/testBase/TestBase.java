package com.toolshop.tests.testBase;

import com.toolshop.base.BasePage;
import com.toolshop.config.PropertiesFileConfig;
import com.toolshop.drivers.BrowserFactory;
import com.toolshop.drivers.DriverFactory;
import com.toolshop.pages.*;
import com.toolshop.utils.LoggerUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class TestBase extends BasePage {

    private static final Logger logger = LoggerUtils.getLogger(TestBase.class);
    DriverFactory driverFactory = DriverFactory.getInstance();
    protected RegistrationPage registrationPage;
    protected LandingPage landingPage;
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected CheckoutPage checkoutPage;

    @BeforeClass
    public void setup() {
        try {
            BrowserFactory browserFactory = new BrowserFactory();

            // Initialize WebDriver (this will also set it in DriverFactory)
            browserFactory.createBrowserInstance("chrome");

            // Retrieve driver (it should already be set)
            WebDriver driver = driverFactory.getDriver();
            if (driver == null) {
                throw new IllegalStateException("WebDriver instance is null after initialization.");
            }

            // Configure browser settings
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            // Navigate to a URL
            driverFactory.navigateToUrl(PropertiesFileConfig.getProperty("PORTAL_BASE_URL"));

            // Initialize pages
            landingPage = new LandingPage(driver);
            registrationPage = new RegistrationPage(driver);
            loginPage = new LoginPage(driver);
            homePage = new HomePage(driver);
            checkoutPage = new CheckoutPage(driver);


            logger.info("Setup initialized successfully.");

        } catch (Exception error) {
            throw new RuntimeException("Error in test setup: Failed to initialize WebDriver.", error);
        }
    }

    @AfterClass
    public void tearDown(){
        try{
            driverFactory.quitDriver();
        } catch (Exception error){
            throw new RuntimeException("WebDriver failed to quit properly.", error);
        }
    }
}
