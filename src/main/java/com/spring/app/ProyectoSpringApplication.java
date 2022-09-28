package com.spring.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EntityScan(basePackages = {"com.spring.app.entities"})
@EnableJpaRepositories(basePackages = {"com.spring.app.repositories"})
public class ProyectoSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoSpringApplication.class, args);
	}

}
