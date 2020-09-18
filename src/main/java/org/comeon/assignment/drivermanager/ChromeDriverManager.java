package org.comeon.assignment.drivermanager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverManager {

    private static final Logger LOG = LogManager.getLogger(ChromeDriverManager.class);

    public WebDriver createWebDriver() {
        try {
            LOG.info("Setting up chrome driver");
            final WebDriverManager chromeDriver = WebDriverManager.chromedriver();
            chromeDriver.setup();
            WebDriver driver = new ChromeDriver();
            LOG.info("Chrome Driver setup successfully");
            return driver;
        } catch (Exception e) {
            LOG.error("Exception setting chrome driver", e);
            throw e;
        }
    }
}