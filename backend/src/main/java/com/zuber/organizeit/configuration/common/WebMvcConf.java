package com.zuber.organizeit.configuration.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;


//@Configuration
//@EnableWebMvc todo wylacza automatyczna konfe mvc ktora boot robi i przez to nie mozna JackSona snake case wlaczyc
// https://stackoverflow.com/questions/7854030/configuring-objectmapper-in-spring
public class WebMvcConf implements WebMvcConfigurer {

}
