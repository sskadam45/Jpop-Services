package com.jpop.bookservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;


@EnableEurekaClient
@SpringBootApplication
@EnableSwagger2
public class BookServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookServiceApplication.class, args);
	}

	@Bean
	public Docket swaggerPersonApi10() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("book-api-1.0")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.jpop.bookservice.controller"))
				.paths(regex("/book/v1.0.*"))
				.build()
				.apiInfo(new ApiInfoBuilder().version("1.0").title("Book API").description("Documentation Book Service API v1.0").build());
	}

	@Bean
	public Docket swaggerPersonApi11() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("book-api-1.1")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.jpop.bookservice.controller"))
				.paths(regex("/book/v1.1.*"))
				.build()
				.apiInfo(new ApiInfoBuilder().version("1.1").title("Book API").description("Documentation Book Service API v1.1").build());
	}

	@Bean
	public Docket swaggerPersonApi12() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("book-api-1.2")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.jpop.bookservice.controller"))
				.paths(regex("/book/v1.2.*"))
				.build()
				.apiInfo(new ApiInfoBuilder().version("1.2").title("Book API").description("Documentation Book Service API v1.2").build());
	}

}
