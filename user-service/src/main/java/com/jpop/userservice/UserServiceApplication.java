package com.jpop.userservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
@EnableHystrix
@EnableHystrixDashboard
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Configuration
	@EnableSwagger2
	public class SwaggerConfig {
		@Bean
		public Docket swaggerPersonApi11() {
			return new Docket(DocumentationType.SWAGGER_2)
					.groupName("user-api-1.1")
					.select()
					.apis(RequestHandlerSelectors.basePackage("com.jpop.userservice.controller"))
					.paths(regex("/user/v1.1.*"))
					.build()
					.apiInfo(new ApiInfoBuilder().version("1.1").title("User API").description("Documentation User Service API v1.1").build());
		}
	}
}
