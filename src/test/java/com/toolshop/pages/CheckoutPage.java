package com.toolshop.pages;

import com.toolshop.tests.testBase.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends TestBase {

    WebDriver driver;

    @FindBy(css = "a[href=\"/checkout\"]")
    private WebElement checkoutLink;

    @FindBy(css = "[data-test='proceed-1']")
    private WebElement proceedToCheckout;

    @FindBy(css = "[data-test='proceed-2']")
    private WebElement loginProceedToCheckout;

    @FindBy(css = "[data-test='proceed-3']")
    private WebElement billingAddressProceedToCheckout;

    @FindBy(css = "#payment-method")
    private WebElement paymentDropdown;

    @FindBy(css = "#credit_card_number")
    private WebElement creditCardNumberInput;

    @FindBy(css = "#expiration_date")
    private WebElement expirationDateInput;

    @FindBy(css = "#cvv")
    private WebElement cvvInput;

    @FindBy(css = "#card_holder_name")
    private WebElement cardHolderInput;

    @FindBy(css = "[data-test='finish']")
    private WebElement confirmButton;

    @FindBy(xpath = "//div[contains(text(), 'Payment was successful')]")
    private WebElement paymentSuccessMessage;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickAddToCartLink(){
        clickElement(checkoutLink, "Add to Cart Link");
    }

    public void clickProceedToCheckout(){
        clickElement(proceedToCheckout, "Proceed to Checkout");
    }

    public void clickLoginProceedToCheckout(){
        clickElement(loginProceedToCheckout, "Proceed to Checkout");
    }

    public void clickBillingAddressProceedToCheckout(){
        clickElement(billingAddressProceedToCheckout, "Proceed to Checkout");
    }

    public void clickPaymentDropdown(String paymentType){
        selectFromDropdown(paymentDropdown, "visibletext", paymentType, "Payment Type");
    }

    public void clickCreditCardNumberInput(String creditCardNumber){
        sendKeys(creditCardNumberInput, creditCardNumber, "Credit card number");
    }

    public void clickExpirationDateInput(String expiryDate){
        sendKeys(expirationDateInput, expiryDate, "Expiration date");
    }

    public void clickCvvInput(String cvv){
        sendKeys(cvvInput, cvv, "Enter cvv");
    }

    public void clickCardHolderInput(String cardHolder){
        sendKeys(cardHolderInput, cardHolder, "Card holder");
    }

    public void clickConfirmButton(){
        clickElement(confirmButton, "Confirm");
    }

    public void isPaymentSuccessMessageVisible(){
        isElementVisible(paymentSuccessMessage, "Payment success message");
    }

    public void checkoutAndPayForItems(
            String paymentType,
            String creditCardNumber,
            String expiryDate,
            String cvv,
            String cardHolder
    ){
        clickAddToCartLink();

        clickProceedToCheckout();
        clickLoginProceedToCheckout();
        clickBillingAddressProceedToCheckout();

        clickPaymentDropdown(paymentType);
        clickCreditCardNumberInput(creditCardNumber);
        clickExpirationDateInput(expiryDate);
        clickCvvInput(cvv);
        clickCardHolderInput(cardHolder);

        clickConfirmButton();
    }
}
