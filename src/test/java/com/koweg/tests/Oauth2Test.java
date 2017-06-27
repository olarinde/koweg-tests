package com.koweg.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import io.restassured.RestAssured;

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
        RestAssured.given()
        .baseUri(BASE_URI)
        .queryParam(RESPONSE_TYPE, "")
        .queryParam(REDIRECT_URI, "")
        .queryParam(CLIENT_ID, "")
        .queryParam(STATE, "");
    }

}
