package com.semicolonafrica.GutendexBooks.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ConfiguratedMethod {


    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
