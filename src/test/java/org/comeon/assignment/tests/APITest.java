package org.comeon.assignment.tests;

import org.comeon.assignment.util.PropertyManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.post;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

/**
 * This class contains tests verification of API's
 */
public class APITest {

    private static String applicationURL;
    private static String playerStatusAPIPath;
    private static String lovedAndRecentlyPlayedAPIPath;

    @BeforeAll
    public static void init() throws IOException {
        PropertyManager pm = PropertyManager.getInstance();
        applicationURL = pm.getApplicationURL();
        playerStatusAPIPath = pm.getPlayerStatusAPIPath();
        lovedAndRecentlyPlayedAPIPath = pm.getLovedAndRecentlyPlayedAPIPath();
    }

    @DisplayName("Testing Player Status API and validating the authentication flag, positive scenario")
    @Test
    public void playerStatusBeforeAuthenticationTest() {

        get(applicationURL + playerStatusAPIPath)
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .assertThat()
                .body("result.map.authenticated",  equalTo("false"))
                .body("result.map.message", equalTo("Sessionen har upphört"));
    }

    @DisplayName("Test Player Status API and verifying Method Not Allowed error when using wrong http method")
    @Test
    public void playerStatusWrongMethodTest() {

        post(applicationURL + playerStatusAPIPath)
                .then()
                .log().ifValidationFails()
                .statusCode(405);
    }

    @DisplayName("Test Player Status API and verifying Not Found error when using wrong http method and URL")
    @Test
    public void playerStatusWrongCallTest() {

        post(applicationURL + playerStatusAPIPath + "badURL")
                .then()
                .log().ifValidationFails()
                .statusCode(404);
    }

    @DisplayName("Testing lovedAndRecentlyPlayed API, and verifying count when user is not logged in")
    @Test
    public void lovedAndRecentPlayedBeforeAuthenticationTest() {

        get(applicationURL + lovedAndRecentlyPlayedAPIPath)
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .assertThat()
                .body("result.loved",  hasSize(0))
                .body("result.recent", hasSize(0));
    }

    @DisplayName("Testing lovedAndRecentlyPlayed API, and verifying Method Not Allowed error when using wrong http method")
    @Test
    public void lovedAndRecentPlayedWrongCallTest() {

        post(applicationURL + lovedAndRecentlyPlayedAPIPath)
                .then()
                .log().ifValidationFails()
                .statusCode(405);
    }
}
