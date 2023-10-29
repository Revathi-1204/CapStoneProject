package com.mnp.donor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Specify the endpoint and allowed origins
        registry.addMapping("/sub/getStatus")
                .allowedOrigins("http://localhost:4200") // Replace with your Angular app's URL
                .allowedMethods("GET", "POST") // Add more methods as needed
                .allowCredentials(true);

        // Additional configuration for the OPTIONS preflight request
        registry.addMapping("/sub/getStatus")
                .allowedOrigins("http://localhost:4200") // Replace with your Angular app's URL
                .allowedMethods("OPTIONS") // Allow the OPTIONS method
                .allowedHeaders("Authorization", "Content-Type")
                .allowCredentials(true)
                .maxAge(3600); // 1 hour (you can adjust this value)
    }
}
