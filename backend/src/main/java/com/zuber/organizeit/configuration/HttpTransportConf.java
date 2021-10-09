package com.zuber.organizeit.configuration;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;

import com.google.api.client.http.apache.v2.ApacheHttpTransport;

import java.util.List;

import static com.zuber.organizeit.configuration.AppSecrets.*;
import static com.zuber.organizeit.configuration.AppSecrets.CALENDAR;


@Configuration
@Profile("dev")
public class HttpTransportConf {

    @Bean
    static HttpTransport httpTransportForGoogleIntegration() {
        return new ApacheHttpTransport();
    }

    @Bean
    GoogleAuthorizationCodeFlow calendarApiAuthFlow() {
        return new GoogleAuthorizationCodeFlow(
                httpTransportForGoogleIntegration(),
                GsonFactory.getDefaultInstance(),
                CLIENT_ID,
                CLIENT_SECRET,
                List.of(CALENDAR_READ_ONLY, CALENDAR)

        );
    }

}
