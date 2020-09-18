package org.comeon.assignment.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * This class contains all elements for the casino page
 */
public class CasinoPO {

    @FindBy(css = "[data-at=\"love-icon\"]")
    private List<WebElement> loveIconList;

    @FindBy(css = "[data-at=\"notification-bar-header\"]")
    private WebElement notificationBarHeader;

    @FindBy(css = "[data-at=\"search-inputfield-casino-nav-bar\"]")
    private WebElement searchField;

    @FindBy(css = "[data-game=\"QKS_NORTHERNSKY\"]")
    private WebElement northenSkyIcon;

    @FindBy(css = ".ReactVirtualized__Collection")
    private WebElement reactGrid;

    @FindBy(css = "[data-at=\"game-support-fun-mode\"]")
    private WebElement supportFunMode;

    public CasinoPO(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public List<WebElement> getLoveIconList() {
        return loveIconList;
    }

    public WebElement getNotificationBarHeader() {
        return notificationBarHeader;
    }

    public WebElement getNorthenSkyIcon() {
        return northenSkyIcon;
    }

    public WebElement getSearchField() {
        return searchField;
    }

    public void searchGame(String gameName) {
        searchField.click();
        searchField.sendKeys(gameName);
    }

    public WebElement getDotsButton() {
        return reactGrid.findElement(By.tagName("button"));
    }

    public WebElement getSupportFunMode() {
        return supportFunMode;
    }
}
