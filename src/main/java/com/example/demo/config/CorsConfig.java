package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                // allow Swagger / browser
                .allowedOriginPatterns("*")

                // HTTP methods
                .allowedMethods(
                        "GET",
                        "POST",
                        "PUT",
                        "DELETE",
                        "OPTIONS"
                )

                // headers
                .allowedHeaders("*")

                // JWT header support
                .exposedHeaders("Authorization")

                // browser safety
                .allowCredentials(false);
    }
}
