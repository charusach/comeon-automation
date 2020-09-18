package org.comeon.assignment.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.comeon.assignment.pageobjects.CasinoPO;
import org.comeon.assignment.pageobjects.GamePO;
import org.comeon.assignment.pageobjects.HomePagePO;
import org.comeon.assignment.util.PageObjectUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.Dimension;

import java.io.IOException;

import static org.comeon.assignment.pageobjects.GamePO.GAME_IFRAME_CONSTANT;
import static org.comeon.assignment.pageobjects.GamePO.GAME_NORTHERN_SKY;
import static org.junit.jupiter.api.Assertions.*;

public class GameFlowTest extends BaseTest {

    private static final Logger LOG = LogManager.getLogger(GameFlowTest.class);
    private static HomePagePO homePagePO;
    private static CasinoPO casinoPO;
    private static GamePO gamePO;

    @DisplayName("Test to verify that user is able to play game using Play without money option")
    @ParameterizedTest
    @CsvFileSource(resources = "/testdata/dimensions.csv")
    public void northernSkyGameFlowTest(int x, int y) throws IOException {
        driver.manage().window().setSize(new Dimension(x, y));
        LOG.info("Load home page");
        homePagePO = new HomePagePO(driver);
        LOG.info("Home page loaded successful");
        homePagePO.getAcceptCookiesButton().click();
        homePagePO.getCasinoLink().click();

        casinoPO = new CasinoPO(driver);
        casinoPO.searchGame(GAME_NORTHERN_SKY);
        assertTrue(casinoPO.getNorthenSkyIcon().isDisplayed(), "Northern Sky Game should be visible after search");
        casinoPO.getDotsButton().click();
        casinoPO.getSupportFunMode().click();
        PageObjectUtil.switchToIFrame(driver, GAME_IFRAME_CONSTANT);

        gamePO = new GamePO(driver);
        PageObjectUtil.waitUntilVisible(driver, gamePO.getContinueButton());
        gamePO.getContinueButton().click();
        assertTrue(gamePO.getSpinButton().isDisplayed(), "Spin button should be visible");

        double betValue = gamePO.getBetValue();
        double pointBeforeSpin = gamePO.getPointValue();
        LOG.info("Bet Value: " + betValue + ", Points before spin: " + pointBeforeSpin);

        gamePO.getSpinButton().click();
        double pointAfterSpin = gamePO.getPointValue();
        LOG.info("Points after spin: " + pointAfterSpin);

        assertNotEquals(pointBeforeSpin, pointAfterSpin, "Point value should be different after spin");
        assertEquals(pointBeforeSpin-betValue, pointAfterSpin, "Point value should be deducted after spin");
    }
}
