package com.github.tomaszplonski.mes_project.config;

import com.github.tomaszplonski.mes_project.service.securityService.AuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthService authService() {
        return new AuthService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authService()).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

            http.authorizeRequests()
                .antMatchers( "/login","/test").permitAll()
                    .antMatchers("/assets/**").permitAll()
                .antMatchers("/","/**").authenticated()
                .antMatchers("/type/add","/order/add").hasRole("FACTORY")
                .antMatchers("/stages/end/active").hasRole("OFFICE")
                .and()
                .csrf()
                .disable()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/",true)
                .and()
                .logout()
                .logoutUrl("/logout")
                .permitAll();
    }

}
