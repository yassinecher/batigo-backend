package com.batigobackend.batigo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CORSConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Autoriser toutes les routes
                        .allowedOrigins("http://localhost:4200") // ✅ Autoriser Angular
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // ✅ Autoriser les méthodes
                        .allowedHeaders("*") // ✅ Autoriser tous les headers
                        .allowCredentials(true);
            }
        };
    }
}
