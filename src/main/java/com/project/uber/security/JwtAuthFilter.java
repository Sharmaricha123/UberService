package com.project.uber.security;


import com.project.uber.entities.User;
import com.project.uber.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserService userService;

//     ----- to handle JWTException--------

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            final String requestTokenHeader = request.getHeader("Authorization");
            if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer")) {
                filterChain.doFilter(request, response);
                return;
            }
            String token = requestTokenHeader.split("Bearer")[1].trim();

            Long userId = jwtService.getUserIdFromToken(token);
            if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                User user = userService.getUserById(userId);
//put user inside security context holder

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
//---- contains information about IP Address-----------------------
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }
            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            handlerExceptionResolver.resolveException(request,response,null,ex);

        }
    }
}

