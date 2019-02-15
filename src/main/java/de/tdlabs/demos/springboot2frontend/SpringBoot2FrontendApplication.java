package de.tdlabs.demos.springboot2frontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@SpringBootApplication
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class SpringBoot2FrontendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot2FrontendApplication.class, args);
	}

}

