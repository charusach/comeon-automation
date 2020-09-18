package org.comeon.assignment.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This class contains all elements for adding money section
 */
public class InsertAndPlayPO {

    public static final String PAYMENT_IFRAME_ID = "paymentIframe";
    public static final String DIGIPASS_RADIO_VALUE = "SECURITY_TOKEN_CODE";

    @FindBy(id = "amount")
    private WebElement amountTextField;

    @FindBy(css = "[data-at=\"deposit-login-submit-button-noaccount\"]")
    private WebElement depositSubmitButton;

    @FindBy(css = "[data-at=\"TRUSTLY-paymentmethod-deposit-and-play\"]")
    private WebElement trustlyButton;

    @FindBy(id = "paymentIframe")
    private WebElement paymentIframe;

    @FindBy(css = "[alt=\"SEB\"]")
    private WebElement sebLink;

    @FindBy(css = ".radio-option")
    private List<WebElement> paymentOptions;

    @FindBy(css = ".core_action_button")
    private WebElement continuePaymentLink;

    @FindBy(name = "loginid")
    private WebElement personalNumberField;

    @FindBy(css = ".message_value")
    private List<WebElement> codesList;

    @FindBy(name = "security_code")
    private WebElement securityCodeField;

    public InsertAndPlayPO(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public WebElement getAmountTextField() {
        return amountTextField;
    }

    public WebElement getDepositSubmitButton() {
        return depositSubmitButton;
    }

    public WebElement getTrustlyButton() {
        return trustlyButton;
    }

    public WebElement getPaymentIframe() {
        return paymentIframe;
    }

    public WebElement getSebLink() {
        return sebLink;
    }

    public Optional<WebElement> getPaymentOptionByValue(String option) {
        for (WebElement webElement : paymentOptions) {
            WebElement childElement = webElement.findElement(By.cssSelector(".radio-native"));
            if (childElement.getAttribute("value").equalsIgnoreCase(option)) {
                return Optional.of(webElement);
            }
        }
        return Optional.empty();
    }

    public WebElement getContinuePaymentLink() {
        return continuePaymentLink;
    }

    public WebElement getPersonalNumberField() {
        return personalNumberField;
    }

    public List<WebElement> getCodesList() {
        return codesList;
    }

    public String getCodes() {
        String codes = null;
        if (codesList != null) {
            codes  = codesList.stream().map(c -> c.getText()).collect( Collectors.joining( "," ) );
        }
        return codes;
    }

    public WebElement getSecurityCodeField() {
        return securityCodeField;
    }
}
