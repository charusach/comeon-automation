package org.comeon.assignment.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.comeon.assignment.pageobjects.CasinoPO;
import org.comeon.assignment.pageobjects.HomePagePO;
import org.comeon.assignment.util.PageObjectUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CasinoPageTest extends BaseTest {

    private static final Logger LOG = LogManager.getLogger(CasinoPageTest.class);
    private static HomePagePO homePagePO;
    private static CasinoPO casinoPO;

    @DisplayName("Test to verify that casino page link is visible on home page")
    @ParameterizedTest
    @CsvFileSource(resources = "/testdata/dimensions.csv")
    public void casinoLinkVisibleTest(int x, int y) {
        driver.manage().window().setSize(new Dimension(x, y));
        LOG.info("Load home page");
        homePagePO = new HomePagePO(driver);
        LOG.info("Home page loaded successful");
        homePagePO.getAcceptCookiesButton().click();

        assertTrue(homePagePO.getCasinoLink().isDisplayed(), "After landing casino link should be visible");
    }

    @DisplayName("Test to verify that casino page is loaded successfully")
    @ParameterizedTest
    @CsvFileSource(resources = "/testdata/dimensions.csv")
    public void casinoPageLoadingTest(int x, int y) {
        driver.manage().window().setSize(new Dimension(x, y));
        LOG.info("Load home page");
        homePagePO = new HomePagePO(driver);
        LOG.info("Home page loaded successful");
        homePagePO.getAcceptCookiesButton().click();

        homePagePO.getCasinoLink().click();
        casinoPO = new CasinoPO(driver);

        assertTrue(casinoPO.getSearchField().isDisplayed(), "After landing game search bar should be visible");
    }

    @DisplayName("Test to verify that user can't add game to favourite and notification is displayed, if not logged in")
    @ParameterizedTest
    @CsvFileSource(resources = "/testdata/dimensions.csv")
    public void selectFavoriteGameTest(int x, int y) throws IOException {
        driver.manage().window().setSize(new Dimension(x, y));
        LOG.info("Load home page");
        homePagePO = new HomePagePO(driver);
        LOG.info("Home page loaded successful");
        homePagePO.getAcceptCookiesButton().click();

        homePagePO.getCasinoLink().click();
        casinoPO = new CasinoPO(driver);

        JavascriptExecutor ex = (JavascriptExecutor)driver;
        ex.executeScript("arguments[0].click();", casinoPO.getLoveIconList().get(0));

        PageObjectUtil.waitUntilVisible(driver, casinoPO.getNotificationBarHeader());
        assertTrue(casinoPO.getNotificationBarHeader().isDisplayed(), "Without adding money notification should be visible");
    }
}
