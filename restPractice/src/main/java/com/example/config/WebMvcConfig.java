package com.example.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MappingJackson2XmlView;

import java.nio.charset.Charset;
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

    /**
     * viewResolver를 사용해보자.
     */
    // TODO : hi
    @Bean(name="jsonView")
    public View getJsonView() {
        MappingJackson2JsonView view = new MappingJackson2JsonView();
        view.setModelKey("data");
        view.setExtractValueFromSingleKeyModel(true);
        return view;
    }

    @Bean(name="xmlView")
    public View getXmlView() {
        MappingJackson2XmlView view = new MappingJackson2XmlView();
        view.setModelKey("data");
        return view;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .setConnectTimeout(Duration.ofMillis(5000))
                .setReadTimeout(Duration.ofMillis(5000))
                .additionalMessageConverters(new StringHttpMessageConverter(Charset.forName("UTF-8")))
                .build();
    }
}
