package com.duquejo.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class GatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServerApplication.class, args);
	}


	@Bean
	public RouteLocator customRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
			.route(p -> p
				.path("/applications/apihotels/**")
				.filters(f -> f.rewritePath("/applications/apihotels/(?<segment>.*)", "/${segment}")
				.addResponseHeader("X-Response-Time", new Date().toString()))
				.uri("lb://HOTELS"))
			.route(p -> p
				.path( "/applications/apirooms/**")
				.filters(f -> f.rewritePath("/applications/apirooms/(?<segment>.*)", "/${segment}")
				.addResponseHeader("X-Response-Time", new Date().toString()))
				.uri("lb://ROOMS"))
			.route(p -> p
				.path( "/applications/apireservations/**")
				.filters(f -> f.rewritePath("/applications/apireservations/(?<segment>.*)", "/${segment}")
					.addResponseHeader("X-Response-Time", new Date().toString()))
				.uri("lb://RESERVATIONS"))
			.build();
	}
}
