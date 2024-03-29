package com.zuber.organizeit.configuration.common;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.zuber.organizeit.domain.ReferenceResource.ReferenceResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.*;
import java.util.regex.Pattern;


//musi byc wylaczone @EnableWebMvc, zeby zadzialalo https://stackoverflow.com/questions/40649177/jackson-is-ignoring-spring-jackson-properties-in-my-spring-boot-application
@Configuration
public class JacksonConf {

    @Primary
    @Bean
    public ObjectMapper jacksonObjectMapper() {
        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator
                .builder()
                .allowIfSubTypeIsArray()
                .allowIfSubType(ArrayList.class)
                .allowIfBaseType(ArrayList.class)   // todo refactor, nie potrzebne raczej to
                .allowIfBaseType(Pattern.compile("java\\.util\\.Collections\\..*"))
                .allowIfBaseType(ReferenceResource.class)
//                .allowIfBaseType(Pattern.compile("java\\.net\\..*"))
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
//        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        objectMapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.JAVA_LANG_OBJECT, JsonTypeInfo.As.PROPERTY);
//        objectMapper.registerSubtypes(VideoReference.class, BookReference.class, ReferenceResource.class, ArrayList.class, URL.class); //todo chyba nie potrzebne
        return objectMapper;
    }



}
