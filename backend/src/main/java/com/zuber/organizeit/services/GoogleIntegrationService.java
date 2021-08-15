package com.zuber.organizeit.services;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zuber.organizeit.configuration.AppSecrets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

@Service
public class GoogleIntegrationService {

    final NetHttpTransport HTTP_TRANSPORT;
    private final GsonFactory gsonFactory;
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR_READONLY);

    RestTemplate restTemplate;

    public GoogleIntegrationService(RestTemplateBuilder restTemplateBuilder) throws GeneralSecurityException, IOException {
        HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        gsonFactory = new GsonFactory();
        this.restTemplate = restTemplateBuilder;
    }

    private Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {

        GoogleClientSecrets clientSecrets = new GoogleClientSecrets()
                .setWeb(
                        new GoogleClientSecrets.Details()
                                .setClientSecret(AppSecrets.clientSecret)
                                .setClientId(AppSecrets.clientId)
                                .setAuthUri(AppSecrets.authUri)
                                .setRedirectUris(List.of(AppSecrets.redirectUri))
                                .setTokenUri(AppSecrets.tokenUri)
                );


        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, gsonFactory, clientSecrets, SCOPES)
                .setAccessType("offline")
                .build();

        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");

    }

    public void getCalendarEvents() {
        new  Calendar.Builder(HTTP_TRANSPORT, gsonFactory, )
               .setApplicationName("organizeit")
    }
}
