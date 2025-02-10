package com.toolshop.tests.toolShop;

import com.toolshop.config.JsonDataReader;
import com.toolshop.config.PropertiesFileConfig;
import com.toolshop.tests.testBase.TestBase;
import com.toolshop.utils.LoggerUtils;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {

    private static final Logger logger = LoggerUtils.getLogger(RegistrationTests.class);
    private static final String Registration_SECTION = "Registration";

    @Test
    public void registerNewUser() {
        try {
            // Navigate to registration page
            landingPage.navigateToRegistrationPage();

            // Register new user
            registrationPage.registerUser(
                    JsonDataReader.getData(Registration_SECTION, "firstName"),
                    JsonDataReader.getData(Registration_SECTION, "lastName"),
                    JsonDataReader.getData(Registration_SECTION, "dateOfBirth"), // date format is yyyy/mm/dd. The years when running on automation they are undefined, this is a bug
                    JsonDataReader.getData(Registration_SECTION, "address"),
                    JsonDataReader.getData(Registration_SECTION, "postCode"),
                    JsonDataReader.getData(Registration_SECTION, "city"),
                    JsonDataReader.getData(Registration_SECTION, "state"),
                    JsonDataReader.getData(Registration_SECTION, "country"),
                    JsonDataReader.getData(Registration_SECTION, "phone"),
                    PropertiesFileConfig.getProperty("PORTAL_USERNAME"),
                    PropertiesFileConfig.getProperty("PORTAL_PASSWORD")
            );
            logger.info("Registration successful");
        } catch (Exception error) {
            throw new RuntimeException(error.getMessage(), error);
        }
    }
}
