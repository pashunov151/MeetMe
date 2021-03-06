package com.meetme.configurations;

import com.meetme.services.impls.OAuth2UserAuthSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    public final UserDetailsService userDetailsService;
    private final OAuth2UserAuthSuccessHandler oAuth2UserAuthSuccessHandler;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfiguration(UserDetailsService userDetailsService, OAuth2UserAuthSuccessHandler oAuth2UserAuthSuccessHandler, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.oAuth2UserAuthSuccessHandler = oAuth2UserAuthSuccessHandler;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/css/**", "/js/**", "/img/**", "/json/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //TODO: fix logout
        http.
                authorizeRequests().
//                requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
        antMatchers("/login**", "/register", "/db/**", "/").permitAll().
                antMatchers("/**").
                authenticated().
                and().
                formLogin().
                loginPage("/login").
                loginProcessingUrl("/users/login").
                failureForwardUrl("/login-error").
                defaultSuccessUrl("/", true).
                and().
                logout().
                logoutUrl("/logout").
                logoutSuccessUrl("/login").
                invalidateHttpSession(true).
                deleteCookies("JSESSIONID").
                and().
                oauth2Login().
                loginPage("/login").
                successHandler(oAuth2UserAuthSuccessHandler);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authManager) throws Exception {
        authManager.
                userDetailsService(userDetailsService).
                passwordEncoder(passwordEncoder);
    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////        http
////
////                .authorizeRequests()
////                .antMatchers("/login", "/register", "/db/**", "/").permitAll().anyRequest().authenticated()
////                .and()
////                .formLogin()
////                .loginPage("/login");
//        http.
//                authorizeRequests().
//                requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
//                antMatchers("/login", "/register", "/db/**", "/").permitAll().
//                antMatchers("/**").authenticated()
//                .and().
//                formLogin().
//                loginPage("/login").
//                loginProcessingUrl("/login/authenticate").
//                successForwardUrl("/asd")
//                .and().
//                logout().
//                logoutUrl("/logout").
//                logoutSuccessUrl("/login").
//                invalidateHttpSession(true).
//                deleteCookies("JSESSIONID").
//                and().
//                oauth2Login().
//                loginPage("/login").
//                successHandler(oAuth2UserAuthSuccessHandler);
//    }
}
