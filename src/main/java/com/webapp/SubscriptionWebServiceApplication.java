package com.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class SubscriptionWebServiceApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(SubscriptionWebServiceApplication.class, args);

	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/client").setViewName("client");
	}

}
