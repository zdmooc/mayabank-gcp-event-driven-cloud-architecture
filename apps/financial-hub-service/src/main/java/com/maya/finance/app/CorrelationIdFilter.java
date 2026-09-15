package com.maya.finance.app;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class CorrelationIdFilter extends OncePerRequestFilter {
    static final String HEADER = "X-Correlation-Id";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String correlationId = request.getHeader(HEADER);
        if (correlationId == null || correlationId.isBlank()) correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        response.setHeader(HEADER, correlationId);
        long start = System.nanoTime();
        try {
            chain.doFilter(request, response);
        } finally {
            MDC.put("durationMs", Long.toString((System.nanoTime() - start) / 1_000_000));
            MDC.put("httpStatus", Integer.toString(response.getStatus()));
            logger.info("request_completed method={} path={}", request.getMethod(), request.getRequestURI());
            MDC.clear();
        }
    }
}
