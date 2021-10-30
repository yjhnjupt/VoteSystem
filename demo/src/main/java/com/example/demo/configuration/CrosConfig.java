package com.example.demo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class CrosConfig extends WebMvcConfigurerAdapter {
      static final String origins[]=new String[]{"GET","POST","PUT","DELETE"};
      public void addCrosMapping(CorsRegistry registry){
          registry.addMapping("/**").allowedOrigins("*").allowCredentials(true).allowedMethods(origins).maxAge(3600);
      }

}
