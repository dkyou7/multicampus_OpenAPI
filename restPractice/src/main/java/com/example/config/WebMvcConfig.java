package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON)
        .favorParameter(true)   // 쿼리 String paramater
        .parameterName("output")    // 지정
        .ignoreAcceptHeader(true)   // accepted header 지정 안해도 됨.
        ;
    }

    /**
     * 정적 리소스 관리
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/photos/**")
                .addResourceLocations("classpath:/photos/")
                .setCacheControl(CacheControl.maxAge(Duration.ofHours(12)));
    }
}
