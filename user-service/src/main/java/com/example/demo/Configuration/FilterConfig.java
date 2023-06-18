package com.example.demo.Configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.Configuration.Filters.JwtFilter;
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean jwtFilter() {
        FilterRegistrationBean filter= new FilterRegistrationBean();
        filter.setFilter(new JwtFilter());

        filter.addInitParameter("/api/users/", "POST");
        // Set the URL patterns that need to be restricted with their corresponding HTTP methods
        // filter.addUrlPatterns("/api/users/*");;
        // filter.addInitParameter("/api/user", "POST");
        return filter;
    }
}
