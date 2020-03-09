package com.smartest.store.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * This class is used to configure the application to use Swagger
 * @author Tiago Santos
 * @since   2020-03-10
 * 
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * The method used for creating the Bean responsible for configure and post
	 * the API.
	 */
	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors
						.basePackage("com.smartest.store.controller"))
				.paths(PathSelectors.regex("/.*")).build().apiInfo(apiInfo());
	}

	/**
	 * The method used for creating the API's info.
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Store API")
				.description("Customer Data")
				.contact("suabandeira@hotmail.com")
				.license("Smartest IT Solutions License")
				.licenseUrl("suabandeira@hotmail.com").version("1.0").build();
	}

}