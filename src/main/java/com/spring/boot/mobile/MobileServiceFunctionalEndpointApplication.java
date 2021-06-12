package com.spring.boot.mobile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import com.spring.boot.mobile.handler.MobileHandler;

@SpringBootApplication
public class MobileServiceFunctionalEndpointApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileServiceFunctionalEndpointApplication.class, args);
	}

	@Bean
	public RouterFunction<ServerResponse> routeFunction(MobileHandler mobileHandler) {
		return RouterFunctions.route()
				// .GET("/hi", serverRequest -> ServerResponse.ok().body("welcome to functional
				// programming")).build();
				// .GET("/hi", serverRequest -> mobileFunctionalEnpoint.sayHello(serverRequest)
				// ).build();
				.GET("/hi", mobileHandler::sayHello)
				.GET("/student", RequestPredicates.accept(MediaType.APPLICATION_XML), mobileHandler::studentXmlResponse)
				.GET("/student", RequestPredicates.accept(MediaType.APPLICATION_JSON),
						mobileHandler::studentJsonResponse)
				.GET("/mobile", mobileHandler::getAllMobiles).GET("/mobile/{mobile-id}", mobileHandler::getMobileById)
				.POST("/mobile", mobileHandler::saveMobile)
				.onError(Throwable.class, (throwable, serverRequest) -> ServerResponse
						.status(HttpStatus.INTERNAL_SERVER_ERROR).body(throwable.getMessage()))
				.build();
	}

}
