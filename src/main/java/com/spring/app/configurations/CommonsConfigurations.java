package com.spring.app.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.spring.app.configurations" +
        "com.spring.app.services" +
        "com.spring.app.mappers" +
        "com.spring.app.repositories"})
public class CommonsConfigurations {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
