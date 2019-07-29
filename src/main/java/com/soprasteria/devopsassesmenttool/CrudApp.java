package com.soprasteria.devopsassesmenttool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class CrudApp extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder  configure(SpringApplicationBuilder  application) {
		return application.sources(CrudApp.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(CrudApp.class, args);
	}
}