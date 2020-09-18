package org.comeon.assignment.factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.comeon.assignment.drivermanager.ChromeDriverManager;
import org.comeon.assignment.drivermanager.FireFoxDriverManager;
import org.comeon.assignment.enums.DriverType;
import org.openqa.selenium.WebDriver;

public class WebDriverFactory {

    private static final Logger LOG = LogManager.getLogger(WebDriverFactory.class);

    public static WebDriver getWebDriver(DriverType driverType) {
        LOG.info("Get WebDriver for driverType: " + driverType);

        WebDriver webDriver;
        switch (driverType) {
            case FIREFOX:
                webDriver = new FireFoxDriverManager().createWebDriver();
                break;
            default:
                webDriver = new ChromeDriverManager().createWebDriver();
        }
        LOG.info("Returning WebDriver: " + webDriver);
        return webDriver;
    }
}
