package com.sample.contosojobs.ContosoJobsApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class ContosoJobsApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ContosoJobsApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ContosoJobsApplication.class, args);
	}

}
