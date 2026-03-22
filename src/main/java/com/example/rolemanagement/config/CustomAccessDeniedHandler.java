package com.example.rolemanagement.config;

import com.example.roleframework.dto.ErrorResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {

        // Set HTTP status to 403 Forbidden
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");

        // Build JSON using your framework's ErrorResponse
        ErrorResponse error = new ErrorResponse("UNAUTHORIZED", accessDeniedException.getMessage());

        // Convert ErrorResponse to JSON string
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(error);

        // Write JSON to response
        response.getWriter().write(json);
    }
}