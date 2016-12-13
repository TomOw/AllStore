package com.shoponeo.configuration;

import com.shoponeo.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Tomasz on 20.10.2016.
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity
                .ignoring()
                .antMatchers("/resources/**");
    }

    @Override
    public void configure(HttpSecurity security) throws Exception {
        security
                //.requiresChannel().anyRequest().requiresSecure()
                //.and()
                .csrf().disable()
                .authorizeRequests()
                //.anyRequest().authenticated()
                .anyRequest().permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/test").permitAll()
                .and()
                .formLogin().loginPage("/#/login")
                .defaultSuccessUrl("/")
                .and()
                .formLogin().failureUrl("/#/login").permitAll()
                .and()
                .httpBasic();

    }
}