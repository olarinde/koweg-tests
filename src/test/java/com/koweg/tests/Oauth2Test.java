package com.koweg.tests;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.koweg.tests.pages.LoginPageObject;

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
        ValidatableResponse response = RestAssured.given()
                        .baseUri(BASE_URI)
                        .queryParam(RESPONSE_TYPE, "token")
                        .queryParam(REDIRECT_URI, "http://invoice-koweg.rhcloud.com/dailylog/dailylogs")
                        .queryParam(CLIENT_ID, "6381de92-d162-44d3-b949-f5ff8d502566")
                        .queryParam(STATE, "active")
                    .when()
                         .redirects().follow(false)
                        .get()
                    .then()
                         .statusCode(is(302))
                         .header("Location", is(notNullValue()));

        String loginUri = response.extract().header("Location");

        System.out.println(response.extract().statusLine());
        System.out.println(loginUri);
//        System.out.println("\n------- ALL HEADERS -------\n" + response.extract().headers().toString());
        
//        WebDriver driver = new FirefoxDriver();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.get(loginUri);
//        
//        LoginPageObject loginPageObject = new LoginPageObject(driver);
//        assertThat(loginPageObject.isInitialised(), is(true));
//        
//        String _branch = "360";
//        String _subAccount = "00";
//        String _account = "4906341";
//        String _password = "12345";
//        loginPageObject.enterCredentials(_branch, _account, _subAccount, _password);
        
        
    }

}
