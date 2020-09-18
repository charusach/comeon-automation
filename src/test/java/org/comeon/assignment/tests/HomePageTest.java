package org.comeon.assignment.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.comeon.assignment.pageobjects.HomePagePO;
import org.comeon.assignment.util.PropertyManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.Dimension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePageTest extends BaseTest {

    private static final Logger LOG = LogManager.getLogger(HomePageTest.class);
    private static HomePagePO homePagePO;

    @DisplayName("Test to verify that the website is loaded successfully after launching")
    @ParameterizedTest
    @CsvFileSource(resources = "/testdata/dimensions.csv")
    public void siteIsLoadingSuccessfullyTest(int x, int y) {
        driver.manage().window().setSize(new Dimension(x, y));
        LOG.info("Load home page");
        homePagePO = new HomePagePO(driver);
        LOG.info("Home page loaded successful");
        homePagePO.getAcceptCookiesButton().click();

        assertTrue(homePagePO.getRegulationsHeader().isDisplayed(), "After landing regulation header should be visible");
    }

    @DisplayName("Test to verify that icons in header are visible")
    @ParameterizedTest
    @CsvFileSource(resources = "/testdata/dimensions.csv")
    public void headerIconsPresentTest(int x, int y) {
        driver.manage().window().setSize(new Dimension(x, y));
        LOG.info("Load home page");
        homePagePO = new HomePagePO(driver);
        LOG.info("Home page loaded successful");
        homePagePO.getAcceptCookiesButton().click();

        assertTrue(homePagePO.getSpelPausLink().isDisplayed(), "After landing spelpaus link in header should be visible");
        assertTrue(homePagePO.getResponsibleGamingLink().isDisplayed(), "After landing responsible gaming link in header should be visible");
        assertTrue(homePagePO.getSelfTestLink().isDisplayed(), "After landing self test link in header should be visible");
    }

    @DisplayName("Test to verify that icons in header are pointing to correct URL")
    @ParameterizedTest
    @CsvFileSource(resources = "/testdata/dimensions.csv")
    public void validateHeaderLinksTest(int x, int y) throws IOException {

        driver.manage().window().setSize(new Dimension(x, y));
        LOG.info("Load home page");
        homePagePO = new HomePagePO(driver);
        LOG.info("Home page loaded successful");
        homePagePO.getAcceptCookiesButton().click();

        PropertyManager pm = PropertyManager.getInstance();
        assertEquals(homePagePO.getSpelPausLink().getAttribute("href"), pm.getGameBreakLink());
        assertEquals(homePagePO.getResponsibleGamingLink().getAttribute("href"), pm.getApplicationURL() + pm.getGameLimits());
        assertEquals(homePagePO.getSelfTestLink().getAttribute("href"), pm.getSelfTestLink());
    }

    @DisplayName("Test to verify that logo is visible in the footer")
    @ParameterizedTest
    @CsvFileSource(resources = "/testdata/dimensions.csv")
    public void validateLogoIsVisibleTest(int x, int y) {
        driver.manage().window().setSize(new Dimension(x, y));
        LOG.info("Load home page");
        homePagePO = new HomePagePO(driver);
        LOG.info("Home page loaded successful");
        homePagePO.getAcceptCookiesButton().click();

        assertTrue(homePagePO.getLogo().isDisplayed(), "Logo should be visible in the home page");
    }

}
