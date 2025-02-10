package com.toolshop.pages;

import com.toolshop.tests.testBase.TestBase;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends TestBase {

    WebDriver driver;

    @FindBy(css = "svg[id=\"Layer_1\"]")
    private WebElement toolShopLogo;

    @FindBy(xpath = "//a[contains(text(), 'Sign in')]\n")
    private WebElement signInLink;

    @FindBy(xpath = "//a[contains(text(), 'Register your account')]")
    private WebElement registerAccountLink;

    public LandingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void isToolShopLogoVisible() {
        isElementVisible(toolShopLogo, "Tool Shop Logo");
    }

    public void clickSignInLink() {
        clickElement(signInLink, "Sign in Link");
    }

    public void clickRegisterAccountLink() {
        clickElement(registerAccountLink, "Register Link");
    }

    public void navigateToRegistrationPage(){
        isToolShopLogoVisible();
        clickSignInLink();
        clickRegisterAccountLink();
    }
}
