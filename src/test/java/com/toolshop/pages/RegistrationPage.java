package com.toolshop.pages;

import com.toolshop.tests.testBase.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RegistrationPage extends TestBase {

    WebDriver driver;

    @FindBy(xpath = "//h3[contains(text(), 'Customer registration')]")
    private WebElement customerRegistrationHeader;

    @FindBy(css = "#first_name")
    private WebElement firstNameInput;

    @FindBy(css = "#last_name")
    private WebElement lastNameInput;

    @FindBy(css = "#dob")
    private WebElement dateOfBirthInput;

    @FindBy(css = "#address")
    private WebElement addressInput;

    @FindBy(css = "#postcode")
    private WebElement postalCodeInput;

    @FindBy(css = "#city")
    private WebElement cityInput;

    @FindBy(css = "#state")
    private WebElement StateInput;

    @FindBy(css = "#country")
    private WebElement countryDropdown;

    @FindBy(css = "#phone")
    private WebElement phoneNumberInput;

    @FindBy(css = "#email")
    private WebElement emailInput;

    @FindBy(css = "#password")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[contains(text(), 'Register')]")
    private WebElement registerButton;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void isRegistrationHeaderVisible(){
        isElementVisible(customerRegistrationHeader, "Registration Header");
    }

    public void enterFirstName(String firstName){
        sendKeys(firstNameInput, firstName, "First Name");
    }

    public void enterLastName(String lastName){
        sendKeys(lastNameInput, lastName, "Last Name");
    }

    public void enterDateOfBirth(String dateOfBirth){
        clearElement(dateOfBirthInput, "Date of Birth");
        System.out.println("Sending Date: " + dateOfBirth);
        sendKeys(dateOfBirthInput, dateOfBirth, "Date Of Birth");
    }

    public void enterAddress(String address){
        sendKeys(addressInput, address, "Address");
    }

    public void enterPostalCode(String postalCode){
        sendKeys(postalCodeInput, postalCode, "Postal Code");
    }

    public void enterCity(String city){
        sendKeys(cityInput, city, "City");
    }

    public void enterState(String state){
        sendKeys(StateInput, state, "State");
    }

    public void selectCountryDropdown(String country){
        selectFromDropdown(countryDropdown, "visibletext", country, "Country");
    }

    public void enterPhoneNumber(String phoneNumber){
        sendKeys(phoneNumberInput, phoneNumber, "Phone Number");
    }

    public void enterEmail(String email){
        sendKeys(emailInput, email, "Email");
    }

    public void enterPassword(String password){
        sendKeys(passwordInput, password, "Password");
    }

    public void clickRegisterButton(){
        clickElement(registerButton, "Register Button");
    }

    public void registerUser(
            String firstName,
            String lastName,
            String dateOfBirth,
            String address,
            String postCode,
            String city,
            String state,
            String country,
            String phone,
            String email,
            String password
    ){
        enterFirstName(firstName);
        enterLastName(lastName);
        enterDateOfBirth(dateOfBirth);
        enterAddress(address);
        enterPostalCode(postCode);
        enterCity(city);
        enterState(state);
        selectCountryDropdown(country);
        enterPhoneNumber(phone);
        enterEmail(email);
        enterPassword(password);
        clickRegisterButton();
    }

    private String formatDate(String date) {
        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            return LocalDate.parse(date, inputFormatter).format(outputFormatter);
        } catch (Exception error) {
            throw new RuntimeException("Date format error: " + error.getMessage(), error);
        }
    }

}
