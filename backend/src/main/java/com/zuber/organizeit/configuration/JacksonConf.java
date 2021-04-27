package com.zuber.organizeit.configuration;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.zuber.organizeit.Model.Reference.ReferenceResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
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
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        objectMapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.JAVA_LANG_OBJECT, JsonTypeInfo.As.PROPERTY);
//        objectMapper.registerSubtypes(VideoReference.class, BookReference.class, ReferenceResource.class, ArrayList.class, URL.class); //todo chyba nie potrzebne


        return objectMapper;

//        new Jackson2ObjectMapperBuilder().build().

    }









//    @Primary
//    @Bean
//    public ObjectMapper jacksonObjectMapper() {
//        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator
//                .builder()
//                .allowIfSubTypeIsArray()
//                .allowIfSubType(ArrayList.class)
//                .allowIfBaseType(ArrayList.class)
//                .allowIfBaseType(Pattern.compile("java\\.util\\.Collections\\..*"))
//                .allowIfBaseType(ReferenceResource.class)
////                .allowIfBaseType(Pattern.compile("java\\.net\\..*"))
//                .build();
//        ObjectMapper objectMapper = new ObjectMapper().setPropertyNamingStrategy(
//                PropertyNamingStrategy.SNAKE_CASE
//        );
//
//        objectMapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.EVERYTHING, JsonTypeInfo.As.PROPERTY);
////        objectMapper.
//        objectMapper.registerSubtypes(VideoReference.class, BookReference.class, ReferenceResource.class, ArrayList.class, URL.class);    // todo dzialalo 1
//
//        return objectMapper;
//
////        new Jackson2ObjectMapperBuilder().build().
//
//    }
}
