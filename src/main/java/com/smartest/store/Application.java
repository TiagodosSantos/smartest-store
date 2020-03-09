package com.smartest.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * This class is used to configure the application to use SpringBoot
 * @author Tiago Santos
 * @since   2020-03-10
 * 
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

}
