package org.comeon.assignment.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class PageObjectUtil {

    public static void maximizeWindow(WebDriver driver) {
        driver.manage().window().maximize();
    }

    public static void switchToIFrame(WebDriver driver, String iframeId) {
        driver.switchTo().frame(iframeId);
    }

    public static void waitForTime(WebDriver driver, int seconds) {
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    public static void waitUntilVisible(WebDriver driver, WebElement element) throws IOException {
        waitForCondition(driver, ExpectedConditions.visibilityOf(element),
                PropertyManager.getInstance().getExplicitWaitTime());
    }

    private static <T> void waitForCondition(WebDriver driver, ExpectedCondition<T> condition, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(condition);
    }
}
