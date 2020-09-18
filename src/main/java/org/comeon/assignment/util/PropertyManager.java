package org.comeon.assignment.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

public class PropertyManager {

    private static final Logger LOG = LogManager.getLogger(PropertyManager.class);

    private static PropertyManager instance;
    private String applicationURL;
    private String browser;
    private Integer implicitWaitTime;
    private Integer explicitWaitTime;
    private String gameBreakLink;
    private String gameLimits;
    private String selfTestLink;
    private String personalId;
    private Integer totalCodes;
    private String paymentAmount;
    private String playerStatusAPIPath;
    private String lovedAndRecentlyPlayedAPIPath;

    public static PropertyManager getInstance () throws IOException {
        if (instance == null) {
            instance = new PropertyManager();
            instance.loadData();
        }
        return instance;
    }

    private void loadData() throws IOException {
        Properties prop = new Properties();
        try {
            LOG.info("Loading property file ");
            prop.load(this.getClass().getClassLoader().getResourceAsStream("application.properties"));

            applicationURL = prop.getProperty("applicationURL", "https://www.snabbare.com");
            browser = prop.getProperty("browser", "FIREFOX");
            implicitWaitTime = Integer.parseInt(prop.getProperty("implicitWaitTime", "10"));
            explicitWaitTime = Integer.parseInt(prop.getProperty("explicitWaitTime", "10"));
            gameBreakLink = prop.getProperty("gameBreakLink", "https://www.spelpaus.se/?scos=true");
            gameLimits = prop.getProperty("gameLimits", "/sv/account/my-limits");
            selfTestLink = prop.getProperty("selfTestLink", "https://stodlinjen.se/#!/spelberoende-test-pgsi");
            personalId = prop.getProperty("personalId", "198909215555");
            totalCodes = Integer.parseInt(prop.getProperty("totalCodes", "2"));
            paymentAmount = prop.getProperty("paymentAmount", "120");
            playerStatusAPIPath = prop.getProperty("playerStatusAPIPath", "/player/status");
            lovedAndRecentlyPlayedAPIPath = prop.getProperty("lovedAndRecentlyPlayedAPIPath", "/rest/state/casino/lovedAndRecentlyPlayed");
        } catch (IOException e) {
            LOG.error("Configuration properties file cannot be found", e);
            throw e;
        }
    }

    public String getApplicationURL() {
        return applicationURL;
    }

    public String getBrowser() {
        return browser;
    }

    public Integer getImplicitWaitTime() {
        return implicitWaitTime;
    }

    public Integer getExplicitWaitTime() {
        return explicitWaitTime;
    }

    public String getGameBreakLink() {
        return gameBreakLink;
    }

    public String getGameLimits() {
        return gameLimits;
    }

    public String getSelfTestLink() {
        return selfTestLink;
    }

    public String getPersonalId() {
        return personalId;
    }

    public Integer getTotalCodes() {
        return totalCodes;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public String getPlayerStatusAPIPath() {
        return playerStatusAPIPath;
    }

    public String getLovedAndRecentlyPlayedAPIPath() {
        return lovedAndRecentlyPlayedAPIPath;
    }
}