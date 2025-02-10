package com.toolshop.pages;

import com.toolshop.tests.testBase.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends TestBase {

    WebDriver driver;

    @FindBy(css = "#email")
    WebElement emailInput;

    @FindBy(css = "#password")
    WebElement passwordInput;

    @FindBy(css = "input[type=\"submit\"]")
    private WebElement loginButton;

    @FindBy(css = "#menu")
    private WebElement profile;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterEmail(String email) {
        sendKeys(emailInput, email, "Email");
    }

    public void enterPassword(String password) {
        sendKeys(passwordInput, password, "Password");
    }

    public void clickLoginButton() {
        clickElement(loginButton, "Login Button");
    }

    public void signIn(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
        isProfileVisible();
    }

    public void isProfileVisible(){
        isElementVisible(profile, "Profile");
    }
}
