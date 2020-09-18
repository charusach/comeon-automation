package org.comeon.assignment.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.comeon.assignment.enums.DriverType;
import org.comeon.assignment.factory.WebDriverFactory;
import org.comeon.assignment.util.PageObjectUtil;
import org.comeon.assignment.util.PropertyManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public abstract class BaseTest {

    private static final Logger LOG = LogManager.getLogger(BaseTest.class);
    public WebDriver driver;

    @BeforeEach
    public void setUp() throws IOException {

        LOG.info("Getting browser type from properties");
        DriverType driverType = DriverType.valueOf(PropertyManager.getInstance().getBrowser());

        LOG.info("Setting up driver");
        driver = WebDriverFactory.getWebDriver(driverType);
        LOG.info("Driver setup successful");

        LOG.info("Navigating to application url: " + PropertyManager.getInstance().getApplicationURL());
        driver.get(PropertyManager.getInstance().getApplicationURL());
        PageObjectUtil.waitForTime(driver, PropertyManager.getInstance().getImplicitWaitTime());
    }

    @AfterEach
    public void tearDown() {
        LOG.info("Quitting WebDriver");
        driver.quit();
    }

}
