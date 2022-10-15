package com.harlaus.config;

import com.harlaus.common.properties.CorsProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class CorsConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private CorsProperty corsProperty;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(corsProperty.getMap())
                .allowedOrigins(corsProperty.getOrigins())
                .allowedMethods(corsProperty.getMethods())
                .maxAge(corsProperty.getAge());
    }
}
