package com.toolshop.tests.toolShop;

import com.toolshop.config.JsonDataReader;
import com.toolshop.config.PropertiesFileConfig;
import com.toolshop.tests.testBase.TestBase;
import com.toolshop.utils.LoggerUtils;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class BuyItemsTests extends TestBase {

    private static final Logger logger = LoggerUtils.getLogger(BuyItemsTests.class);
    private static final String PAYMENT_INFO = "PaymentInformation";

    @Test
    public void testBuyItems() {
        try {
            landingPage.clickSignInLink();

            loginPage.signIn(PropertiesFileConfig.getProperty("PORTAL_USERNAME"), PropertiesFileConfig.getProperty("PORTAL_PASSWORD"));

            homePage.addItemsToCart();

            checkoutPage.checkoutAndPayForItems(
                    JsonDataReader.getData(PAYMENT_INFO, "paymentMethod"),
                    JsonDataReader.getData(PAYMENT_INFO, "cardNumber"),
                    JsonDataReader.getData(PAYMENT_INFO, "expiryDate"),
                    JsonDataReader.getData(PAYMENT_INFO, "cvv"),
                    JsonDataReader.getData(PAYMENT_INFO, "cardHolder")
            );

            checkoutPage.isPaymentSuccessMessageVisible();
            logger.info("Checkout successful");
        } catch (Exception error) {
            throw new RuntimeException(error.getMessage(), error);
        }
    }
}
