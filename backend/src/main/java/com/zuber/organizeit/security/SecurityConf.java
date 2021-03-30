package com.zuber.organizeit.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConf extends WebSecurityConfigurerAdapter {


    //todo implement security
    @Override
    public void configure(HttpSecurity conf) throws Exception {
        conf
                .cors().and()
                .csrf().disable()
                .authorizeRequests().antMatchers("/**").permitAll();
    }

}
