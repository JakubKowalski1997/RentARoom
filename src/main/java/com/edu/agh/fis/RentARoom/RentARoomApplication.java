package com.edu.agh.fis.RentARoom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Main class of application
 *
 *
 * @author  Jakub Kowalski
 * @version 1.0
 * @since   2020-05-30
 */

@SpringBootApplication
@EnableAsync
public class RentARoomApplication  extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder) {
		return applicationBuilder.sources(RentARoomApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(RentARoomApplication.class, args);
	}

}
