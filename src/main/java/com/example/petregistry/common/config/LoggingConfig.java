package com.example.petregistry.common.config;

import org.slf4j.MDC;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Configuration
public class LoggingConfig {

    /**
     * Filter que añade un requestId único a cada petición, disponible
     * en el MDC (Mapped Diagnostic Context) bajo la clave "requestId".
     * Así podrás referenciarlo en tu patrón de logback/log4j:
     *     [%X{requestId}] %-5level %logger{36} - %msg%n
     */
    @Bean
    public FilterRegistrationBean<OncePerRequestFilter> mdcFilter() {
        FilterRegistrationBean<OncePerRequestFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain)
                    throws ServletException, IOException {
                String traceId = UUID.randomUUID().toString();
                MDC.put("requestId", traceId);
                try {
                    chain.doFilter(request, response);
                } finally {
                    MDC.remove("requestId");
                }
            }
        });
        registration.setOrder(1);
        return registration;
    }

    /**
     * Filter que registra información básica de cada petición HTTP:
     * cliente, URI, queryString y payload (hasta un cierto tamaño).
     */
    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeClientInfo(true);
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(10_240);
        filter.setIncludeHeaders(false);
        filter.setAfterMessagePrefix("REQUEST DATA: ");
        return filter;
    }
}