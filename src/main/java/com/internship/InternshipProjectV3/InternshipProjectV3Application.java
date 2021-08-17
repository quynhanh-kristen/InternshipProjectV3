package com.internship.InternshipProjectV3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@ComponentScan(basePackages = {"com.internship"})
@EnableJpaRepositories(basePackages = {"com.internship.repository"})
@EntityScan(basePackages = {"com.internship.model"})
public class InternshipProjectV3Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(InternshipProjectV3Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(InternshipProjectV3Application.class, args);
	}

}
