package com.koweg.tests;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import com.koweg.tests.pages.LoginPageObject;
import com.koweg.tests.pages.PermissionGrantPageObject;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

public class Oauth2Test {

    private static final String BASE_URI = "https://uat1.simulator-api.db.com/gw/oidc/authorize";
    private static final String RESPONSE_TYPE = "response_type";
    private static final String REDIRECT_URI = "redirect_uri";
    private static final String CLIENT_ID = "client_id";
    private static final String STATE = "state";

    @Test
    public void oauth2_authorisation_request_test() {
        final ValidatableResponse response = RestAssured
                .given()
                  .proxy("surf-proxy.intranet.db.com", 8080)
                  .baseUri(BASE_URI).queryParam(RESPONSE_TYPE, "token")
                  .queryParam(REDIRECT_URI, "http://invoice-koweg.rhcloud.com/dailylog/dailylogs")
                  .queryParam(CLIENT_ID, "6381de92-d162-44d3-b949-f5ff8d502566")
                  .queryParam(STATE, "active")
                .when()
                  .redirects()
                  .follow(false)
                  .get()
                .then()
                  .statusCode(is(302))
                  .header("Location", is(notNullValue())
                );

        final String loginUri = response.extract().header("Location");

        System.out.println(response.extract().statusLine());
        // System.out.println("\n------- ALL HEADERS -------\n" +
        // response.extract().headers().toString());

        WebDriver driver = loadDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(loginUri);

        LoginPageObject loginPageObject = new LoginPageObject(driver);
        assertThat(loginPageObject.isInitialised(), is(true));

        String _branch = "100";
        String _subAccount = "98";
        String _account = "0000024";
        String _password = "72105";
        loginPageObject.enterCredentials(_branch, _account, _subAccount, _password);
        
        PermissionGrantPageObject permissionGrantPageObject = loginPageObject.submitCredentials();
        assertThat(permissionGrantPageObject.isInitialised(), is(true));
        permissionGrantPageObject.grantAll();
        permissionGrantPageObject.submitCredentials();
        
        driver.close();
    }

    private WebDriver loadDriver() {
        System.setProperty("webdriver.gecko.driver", "C://Users/LDK090/opt/geckodriver.exe");
        File firefoxPath = new File("C://Users/LDK090/opt/firefox/Firefox.exe");
        FirefoxBinary firefoxBinary = new FirefoxBinary(firefoxPath);
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        return new FirefoxDriver(firefoxBinary, firefoxProfile);
    }

}
