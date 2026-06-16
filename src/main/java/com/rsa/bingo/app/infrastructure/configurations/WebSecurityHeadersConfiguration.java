package com.rsa.bingo.app.infrastructure.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;

import java.io.IOException;

@Configuration
public class WebSecurityHeadersConfiguration implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean<SecurityHeadersFilter> securityHeadersFilter() {
        var registration = new FilterRegistrationBean<SecurityHeadersFilter>();
        registration.setFilter(new SecurityHeadersFilter());
        registration.addUrlPatterns("/*");
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registration;
    }

    static class SecurityHeadersFilter implements Filter {
        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                throws IOException, ServletException {
            if (response instanceof HttpServletResponse httpResponse) {
                httpResponse.setHeader("X-Content-Type-Options", "nosniff");
                httpResponse.setHeader("X-Frame-Options", "DENY");
                httpResponse.setHeader("X-XSS-Protection", "1; mode=block");
                httpResponse.setHeader("Referrer-Policy", "strict-origin-when-cross-origin");
                httpResponse.setHeader("Permissions-Policy", "geolocation=(), camera=(), microphone=()");
            }
            chain.doFilter(request, response);
        }
    }
}
