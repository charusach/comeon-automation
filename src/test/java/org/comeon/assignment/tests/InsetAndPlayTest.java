package org.comeon.assignment.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.comeon.assignment.pageobjects.HomePagePO;
import org.comeon.assignment.pageobjects.InsertAndPlayPO;
import org.comeon.assignment.util.PageObjectUtil;
import org.comeon.assignment.util.PropertyManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.Dimension;

import java.io.IOException;

import static org.comeon.assignment.pageobjects.InsertAndPlayPO.DIGIPASS_RADIO_VALUE;
import static org.junit.jupiter.api.Assertions.*;

public class InsetAndPlayTest extends BaseTest {

    private static final Logger LOG = LogManager.getLogger(InsetAndPlayTest.class);
    private static HomePagePO homePagePO;
    private static InsertAndPlayPO insertAndPlayPO;
    private static PropertyManager propertyManager;

    @BeforeAll
    public static void init() throws IOException {
        propertyManager = PropertyManager.getInstance();
    }

    @DisplayName("Test to verify the payment flow")
    @ParameterizedTest
    @CsvFileSource(resources = "/testdata/dimensions.csv")
    public void insertAndPlayTest(int x, int y) throws IOException {

        driver.manage().window().setSize(new Dimension(x, y));

        LOG.info("Load home page");
        homePagePO = new HomePagePO(driver);
        LOG.info("Home page loaded successful");

        homePagePO.getAcceptCookiesButton().click();
        homePagePO.getDepositPlayButton().click();

        insertAndPlayPO = new InsertAndPlayPO(driver);
        insertAndPlayPO.getAmountTextField().sendKeys(propertyManager.getPaymentAmount());
        insertAndPlayPO.getDepositSubmitButton().click();
        insertAndPlayPO.getTrustlyButton().click();

        PageObjectUtil.waitUntilVisible(driver, insertAndPlayPO.getPaymentIframe());
        PageObjectUtil.switchToIFrame(driver, insertAndPlayPO.PAYMENT_IFRAME_ID);
        PageObjectUtil.waitUntilVisible(driver, insertAndPlayPO.getSebLink());
        insertAndPlayPO.getSebLink().click();

        assertTrue(insertAndPlayPO.getPaymentOptionByValue(DIGIPASS_RADIO_VALUE).isPresent(), "Digipass radio button should be present");
        assertTrue(insertAndPlayPO.getPaymentOptionByValue(DIGIPASS_RADIO_VALUE).get().isDisplayed(), "Digipass radio button should be displayed");
        insertAndPlayPO.getPaymentOptionByValue(DIGIPASS_RADIO_VALUE).ifPresent(e -> e.click());

        assertTrue(insertAndPlayPO.getPersonalNumberField().isDisplayed(), "Field for entering personal number should be displayed");

        insertAndPlayPO.getPersonalNumberField().sendKeys(propertyManager.getPersonalId());
        insertAndPlayPO.getContinuePaymentLink().click();

        assertEquals(propertyManager.getTotalCodes(), insertAndPlayPO.getCodesList().size());
        assertNotNull(insertAndPlayPO.getCodesList().get(0), "Kontrollkod 1 should not be NULL");
        assertNotNull(insertAndPlayPO.getCodesList().get(1), "Kontrollkod 2 should not be NULL");
        LOG.info("Codes values: "+ insertAndPlayPO.getCodes());
        assertTrue(insertAndPlayPO.getSecurityCodeField().isDisplayed(), "Field for entering security code should be displayed");
    }

}
