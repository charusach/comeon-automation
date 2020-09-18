package org.comeon.assignment.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class contains all elements for the home page
 */
public class HomePagePO {

    @FindBy(id = "regulations-header")
    private WebElement regulationsHeader;

    @FindBy(css = "[data-at=\"regulation-header-spel-paus\"]")
    private WebElement spelPausLink;

    @FindBy(css = "[data-at=\"regulation-header-responsible-gaming\"]")
    private WebElement responsibleGamingLink;

    @FindBy(css = "[data-at=\"regulation-header-self-test\"]")
    private WebElement selfTestLink;

    @FindBy(css = "[data-at=\"spelinspektionen-logo\"]")
    private WebElement logo;

    @FindBy(css = ".btn.btn--ghost")
    private WebElement acceptCookiesButton;

    @FindBy(css = "[data-at=\"product-casino\"]")
    private WebElement casinoLink;

    @FindBy(css = "[data-at=\"deposit-and-play-navbar-noaccount\"]")
    private WebElement depositPlayButton;


    public HomePagePO(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public WebElement getRegulationsHeader() {
        return regulationsHeader;
    }

    public WebElement getSpelPausLink() {
        return spelPausLink;
    }

    public WebElement getResponsibleGamingLink() {
        return responsibleGamingLink;
    }

    public WebElement getSelfTestLink() {
        return selfTestLink;
    }

    public WebElement getLogo() {
        return logo;
    }

    public WebElement getAcceptCookiesButton() {
        return acceptCookiesButton;
    }

    public WebElement getCasinoLink() {
        return casinoLink;
    }

    public WebElement getDepositPlayButton() {
        return depositPlayButton;
    }

}
