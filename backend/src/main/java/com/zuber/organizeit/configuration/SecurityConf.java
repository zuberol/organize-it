package com.zuber.organizeit.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@Profile("dev")
public class SecurityConf extends WebSecurityConfigurerAdapter {

//    //todo implement security
    @Override
    public void configure(HttpSecurity conf) throws Exception {
        conf
                .cors().and()
                .csrf().disable()
                .authorizeRequests().antMatchers("/**").permitAll();
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////        http
////                .antMatcher("/integrations/**").
////                .authorizeRequests(authorize -> authorize
//////                        .mvcMatchers("/integrations**").permitAll()
//////                        .mvcMatchers("/login/oauth2/code/google-login**").permitAll()
////                        .anyRequest().authenticated()
////                );
//
//        http
//                .authorizeRequests(
//                        auth -> auth
//                                .mvcMatchers("/integrations/**").permitAll()
//                )
//                .authorizeRequests(authorize -> authorize
//                        .anyRequest().authenticated()
//                )
//                .oauth2Login(withDefaults());
//    }

}
