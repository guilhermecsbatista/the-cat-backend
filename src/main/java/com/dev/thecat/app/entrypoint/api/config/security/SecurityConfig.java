package com.dev.thecat.app.entrypoint.api.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
  public void configure(final AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(
        super.userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
  }

  @Configuration
  @Order(1)
  public static class BasicSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(final WebSecurity web) {
      web.ignoring()
          .antMatchers(
              "/v3/api-docs/**", "/swagger-resources/**", "/swagger-ui/**",
              "/swagger-ui.html", "/webjars/**", "/h2/**", "/actuator/health", "/health", "/docs");
    }

    protected void configure(final HttpSecurity http) throws Exception {
      http
          .requestMatchers()
          .antMatchers("/actuator/**")
          .antMatchers("/swagger-ui/**")
          .antMatchers("/v3/api-docs/**")
          .and()
          .authorizeRequests()
          .anyRequest()
          .authenticated()
          .and()
          .httpBasic()
          .and()
          .csrf()
          .disable();
    }
  }

  @Configuration
  @Order(2)
  public static class ApiTokenSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

    @Value("${application.security.jwt.secretKey}")
    private String jwtSecretKey;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
      http
          .headers()
          .frameOptions()
          .disable()
          .and()
          .authorizeRequests()
          .antMatchers("/**")
          .authenticated()
          .and()
          .addFilter(new JwtAuthenticationFilterConfig(super.authenticationManager(), jwtSecretKey))
          .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
          .and()
          .csrf()
          .disable();
    }
  }
}
