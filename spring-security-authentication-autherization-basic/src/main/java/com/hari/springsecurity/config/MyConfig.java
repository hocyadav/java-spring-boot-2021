package com.hari.springsecurity.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author HariomYadav
 * @since 26/01/21
 */
@EnableWebSecurity
public class MyConfig extends WebSecurityConfigurerAdapter {
    //1. authentication (types : username password based, oauth based, sso based,LDAP based, JWT based, kerberos based..
    // so single app can have multiple authentication way)
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("foo").password("foo").roles("USER")
            .and()
            .withUser("blah").password("blah").roles("ADMIN");
    }

    //2. authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/admin").hasRole("ADMIN")
            .antMatchers("/user").hasAnyRole("USER", "ADMIN")
            .antMatchers("/").permitAll()
            .antMatchers("/h2-console").permitAll()
            .and().formLogin();
        http.csrf().disable();
    }
}
