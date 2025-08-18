package com.ooad.inventorymanagmentsystem.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Make files from 'public/images/receipts' folder accessible via '/images/receipts/**'
        registry.addResourceHandler("/images/receipts/**")
                .addResourceLocations("file:public/images/receipts/"); // Path to the folder where receipts are stored
    }
}
