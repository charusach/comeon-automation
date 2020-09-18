package org.comeon.assignment.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class contains all elements for the Game
 */
public class GamePO {

    public static final String GAME_IFRAME_CONSTANT = "iframe-game";
    public static final String GAME_NORTHERN_SKY = "Northern Sky";

    @FindBy(id = "ContinueButton")
    private WebElement continueButton;

    @FindBy(id = "DefaultSpinButton")
    private WebElement spinButton;

    @FindBy(id = "balancefield_number")
    private  WebElement pointValue;

    @FindBy(id = "totalbetbutton_number")
    private  WebElement betValue;


    public GamePO(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public WebElement getContinueButton() {
        return continueButton;
    }

    public WebElement getSpinButton() {
        return spinButton;
    }

    public double getPointValue() {
        return Double.parseDouble(pointValue.getText());
    }

    public double getBetValue() {
        return Double.parseDouble(betValue.getText());
    }
}
