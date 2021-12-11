package com.zuber.organizeit.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

@Configuration
@EnableWebSecurity
@Profile("dev")
@Order(1)
public class DevWebConf extends WebSecurityConfigurerAdapter {

    //todo implement security
    @Override
    public void configure(HttpSecurity conf) throws Exception {
        conf.
                headers().addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)).and() //dev todo h2
                .cors().and()
                .csrf().disable()
                .authorizeRequests().antMatchers("/**").permitAll();
    }

//    http.headers().addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsMode.SAMEORIGIN));

}
