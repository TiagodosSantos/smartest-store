package com.smartest.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * This class is used to configure the application to use SpringBoot
 * @author Tiago Santos
 * @since   2020-03-10
 * 
 */
@SpringBootApplication
@EnableAspectJAutoProxy
//@EnableSpringDataWebSupport
@EnableSwagger2
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

}
