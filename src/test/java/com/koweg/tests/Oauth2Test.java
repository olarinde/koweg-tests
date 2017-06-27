package com.koweg.tests;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

public class Oauth2Test {

    private static final String BASE_URI = "https://uat1.simulator-api.db.com/gw/oidc/authorize";
    private static final String RESPONSE_TYPE = "response_type";
    private static final String REDIRECT_URI = "redirect_uri";
    private static final String CLIENT_ID = "client_id";
    private static final String STATE = "state";

//    GET 
//    https://uat1.simulator-api.db.com/gw/oidc/authorize
//         ?response_type=code&
//          redirect_uri=<your_redirect_uri>&
//          client_id=<your_client_id>&
//          state=<your_state>


    @Test
    public void oauth2_authorisation_request_test() {
        ValidatableResponse response = RestAssured.given()
                    .baseUri(BASE_URI)
                        .queryParam(RESPONSE_TYPE, "token")
                        .queryParam(REDIRECT_URI, "http://invoice-koweg.rhcloud.com/dailylog/dailylogs")
                        .queryParam(CLIENT_ID, "6381de92-d162-44d3-b949-f5ff8d502566")
                        .queryParam(STATE, "active")
                    .when()
                        .get()
                    .then()
                         .statusCode(is(200));
        System.out.println(response.extract().asString());
    }

}
