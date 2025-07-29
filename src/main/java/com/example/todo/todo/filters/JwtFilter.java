package com.example.todo.todo.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.todo.todo.services.UserServices;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    UserServices userServices;

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request)
            throws ServletException {
        return request.getRequestURI().startsWith("/api/v1/users/auth/");
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws IOException, ServletException {
        String authHeaders = request.getHeader("Authorization");
        if (authHeaders == null) {
            rejectWithUnuthorized("Token missing", response);

            return;
        }

        String token = authHeaders.substring(7);
        UserDetails user = userServices.userDetails(token);
        if (user == null) {
            rejectWithUnuthorized("Invalid token", response);

            return;
        }

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null,
                    user.getAuthorities());
            authToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request, response);
    }

    private void rejectWithUnuthorized(String message, HttpServletResponse response)
            throws IOException {
        response.setStatus(401);
        response.setContentType("application/json");
        response.getWriter()
                .write(String.format(
                        "{\"error\": \"%s\" }",
                        message));
    }
}
