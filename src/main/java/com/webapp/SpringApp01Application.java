package com.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class SpringApp01Application extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(SpringApp01Application.class, args);

	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/client").setViewName("index.html");
	}

}
