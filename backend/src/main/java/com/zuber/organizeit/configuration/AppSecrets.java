package com.zuber.organizeit.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class AppSecrets {
    public static final String CLIENT_ID = "407066747704-s51j64j8lr7p9fjqigso5ca5c2a8ahr5.apps.googleusercontent.com";
    public static final String CLIENT_SECRET = "AV91UFmdvR-D1Ce1oUOivMmr";

    public static final String PROJECT_ID = "calendarapp-1613317628063";
    public static final String AUTH_URI = "https://accounts.google.com/o/oauth2/auth";
    public static final String TOKEN_URI = "https://oauth2.googleapis.com/token";
    public static final String REDIRECT_URI = "http://localhost:8080/integrations/google";

    // todo zrobic enuma z tego
    public static final String CALENDAR_READ_ONLY = "https://www.googleapis.com/auth/calendar.readonly";
    public static final String CALENDAR = "https://www.googleapis.com/auth/calendar";




}