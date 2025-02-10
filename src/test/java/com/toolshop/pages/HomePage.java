package com.toolshop.pages;

import com.toolshop.tests.testBase.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends TestBase {

    WebDriver driver;

    @FindBy(xpath = "//a[contains(text(), 'Home')]")
    private WebElement homeLink;

    @FindBy(css = "[alt='Combination Pliers']")
    private WebElement combinationPliers;

    @FindBy(css = "[alt='Bolt Cutters']")
    private WebElement boltCutters;

    @FindBy(css = "#btn-add-to-cart")
    private WebElement addToCart;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickHomeLink() {
        clickElement(homeLink, "Home");
    }

    public void clickCombinationPliers() {
        clickElement(combinationPliers, "Combination Pliers");
        clickAddToCart();
    }

    public void clickBoltCutters() {
        clickElement(boltCutters, "Bolt Cutters");
        clickAddToCart();
    }

    public void clickAddToCart() {
        clickElement(addToCart, "Add to Cart");
    }

    public void addItemsToCart(){
        clickHomeLink();
        clickCombinationPliers();
        clickAddToCart();

        clickHomeLink();
        clickBoltCutters();
        clickAddToCart();
    }
}
