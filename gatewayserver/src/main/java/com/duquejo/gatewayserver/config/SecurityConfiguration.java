package com.duquejo.gatewayserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {

  @Bean
  public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http ) {
    http.authorizeExchange(exchanges -> exchanges
        .pathMatchers("/applications/apihotels/**").hasRole("hotels") // Must authenticate
        .pathMatchers("/applications/apirooms/**").authenticated() // Must authenticate
        .pathMatchers("/applications/apireservations/**").permitAll() // No authentication
    )
      .oauth2ResourceServer()
      .jwt()
      .jwtAuthenticationConverter(grantedAuthorities());
    http.csrf().disable();
    return http.build();
  }

  Converter<Jwt, Mono<AbstractAuthenticationToken>> grantedAuthorities() {
    JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
    jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new RoleKeycloakConverter());
    return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
  }
}
