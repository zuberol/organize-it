package com.zuber.organizeit.controllers;


import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.zuber.organizeit.configuration.AppSecrets;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static com.zuber.organizeit.configuration.AppSecrets.*;

@RestController
@RequestMapping("/integrations")
public class IntegrationsController {

    HttpTransport httpTransport;
    GoogleAuthorizationCodeFlow calendarApiAuthFlow;

    public IntegrationsController(HttpTransport httpTransport, GoogleAuthorizationCodeFlow calendarApiAuthFlow) {
        this.httpTransport = httpTransport;
        this.calendarApiAuthFlow = calendarApiAuthFlow;
    }


    @GetMapping("/google")
    public List<String> googleLogin(@RequestParam("code") String authCode, @RequestParam List<String> scope) throws IOException {
        String userName = "zeauberg";
        System.out.println(authCode);
        System.out.println(scope);

        GoogleAuthorizationCodeTokenRequest googleAuthorizationCodeTokenRequest = calendarApiAuthFlow.newTokenRequest(authCode);
        googleAuthorizationCodeTokenRequest.setRedirectUri(REDIRECT_URI);
        GoogleTokenResponse tokenResponse = googleAuthorizationCodeTokenRequest.execute();

        return new Calendar(
                httpTransport,
                GsonFactory.getDefaultInstance(),
                calendarApiAuthFlow.createAndStoreCredential(tokenResponse, userName)
        ).calendarList().list().execute().getItems().stream().map(CalendarListEntry::getId).collect(Collectors.toList());

    }

    @GetMapping("/google/connect")
    public ResponseEntity<Void> redirectToGoogle(String userId) {
        GoogleAuthorizationCodeRequestUrl codeRequestUrl = calendarApiAuthFlow.newAuthorizationUrl();
        codeRequestUrl.setRedirectUri(REDIRECT_URI);
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(codeRequestUrl.build())).build();

    }


//http://localhost:8080/login?error#access_token=ya29.a0ARrdaM_FMeUMXAFf8AqX8v1YX_4CIdnIj8aWpu7fDTsis9662SnvZXiIcFprFQwgFaICOKD5F9oO_rLoPCY3CKdB2cOUiyfmJJijrx_XWrCwrnSBMa0GhpT8_hLlAum02_3sFBUW26Bo-8mCKSHyUFjWdxx3&token_type=Bearer&expires_in=3599&scope=https://www.googleapis.com/auth/calendar

}
