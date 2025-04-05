package com.logging.correlation.feign;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class InternalHeadersFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // Extract headers from the request
        Map<String, String> headers = new HashMap<>();
        headers.put(InternalHeadersContext.X_INTERNAL_CORRELATION_ID_HEADER,
                httpRequest.getHeader(InternalHeadersContext.X_INTERNAL_CORRELATION_ID_HEADER));
        headers.put(InternalHeadersContext.X_INTERNAL_REQUEST_ID_HEADER,
                httpRequest.getHeader(InternalHeadersContext.X_INTERNAL_REQUEST_ID_HEADER));
        headers.put(InternalHeadersContext.X_INTERNAL_USER_ID_HEADER,
                httpRequest.getHeader(InternalHeadersContext.X_INTERNAL_USER_ID_HEADER));

        // Update the context with headers
        InternalHeadersContext.setInternalHeaders(headers);

        try {
            chain.doFilter(request, response);
        } finally {
            InternalHeadersContext.clear(); // Cleanup to avoid memory leaks
        }
    }
}
