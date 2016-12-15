package com.shoponeo.configuration;

import com.shoponeo.configuration.security.RestUnauthorizedEntryPoint;
import com.shoponeo.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

/**
 * Created by Tomasz on 20.10.2016.
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    private RestUnauthorizedEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private AccessDeniedHandler restAccessDeniedHandler;

    @Autowired
    private AuthenticationSuccessHandler restAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler restAuthenticationFailureHandler;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/index.html", "/login.html",
                "/partials/**", "/template/**", "/", "/error/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/failure").permitAll()
                .antMatchers("/v2/api-docs").hasAnyAuthority("admin")
                .antMatchers("/users/**").hasAnyAuthority("admin")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .accessDeniedHandler(restAccessDeniedHandler)
                .and()
                .formLogin()
                .loginProcessingUrl("/authenticate")
                .successHandler(restAuthenticationSuccessHandler)
                .failureHandler(restAuthenticationFailureHandler)
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
                .deleteCookies("JSESSIONID")
                .permitAll()
                .and();
    }
}